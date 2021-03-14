package io.hari.demo;

import io.hari.demo.config.AppConfig;
import io.hari.demo.dao.*;
import io.hari.demo.entity.*;
import io.hari.demo.entity.onetomanybi.Seat;
import io.hari.demo.entity.onetomanybi.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	AppConfig config;

	@Autowired
	PersonDao personDao;

	@Autowired
	AddressDao addressDao;

	@Autowired
	OrderDao orderDao;

	@Autowired
	GroupsDao groupsDao;

	@Autowired
	UsersDao usersDao;

	@Autowired
	ShowDao showDao;

	@Autowired
	SeatDao seatDao;


	@Override
	public void run(String... args) throws Exception {
		//many to many and one to many : both way i.e. 2 way exampple , see Group and User entity
		//STEP 1
		final User hari = User.builder().name("hari").build();//1 simple entity : no relation
		final User om = User.builder().name("om").build();//1 simple entity : no relation
		usersDao.save(hari);//2 save to db, then we can map to many grps
		usersDao.save(om);//2 save to db, then we can map to many grps

		//STEP 2: use case 1 : grp can have same users
		//different grp can store same user: M:M in owner side + fetch eager type and other side simple entity no annotaion
		//we will not use cascade since we will store obj before passing to owner side, see above we are storing other entity
		groupsDao.save(Group.builder().name("grp 1")
				.users(Arrays.asList(hari, om)).build());
		groupsDao.save(Group.builder().name("grp 2")
				.users(Arrays.asList(hari)).build());

		//STEP 2: use case 2 : grps cant have same user, just replace M:M to 1:M
		//different user in each group , : owner side 1:M + eager type , and other side no annotaion
//		groupsDao.save(Group.builder().name("grp 1")
//				.users(Arrays.asList(om)).build());
//		groupsDao.save(Group.builder().name("grp 2")
//				.users(Arrays.asList(hari)).build());

		//STEP 3:  Testing 2 way by adding one DAO method in owner entity side, this will work in both above cases
		final List<Group> allByUsers_id = groupsDao.findAllByUsers_Id(1L);
		System.err.println("allByUsers_id = " + allByUsers_id);
		allByUsers_id.forEach(group -> {
			final List<User> users = group.getUsers();
			System.err.println("users = " + users);
		});
		// ---------------------------------------


		System.out.println("config = " + config);

		personDao.save(Person.builder().name("hariom").age(31)
				.address(Address.builder().line1("line 1").pincode(495452).build())//cascade persist
				.build());
		final List<Person> allByAddress_id = personDao.findAllByAddress_Id(1L);
		System.out.println("allByAddress_id = " + allByAddress_id);

		orderDao.save(Order.builder().cusId("1234")
				.items(Arrays.asList(Item.builder().name("item 1").price(123).build()))
				.build());

		final List<Order> allByItems_id = orderDao.findAllByItems_id(1L);
		System.out.println("allByItems_id = " + allByItems_id);

		//todo 2 way - bidirectional entity test
		final Show show = Show.builder().name("show 01")//1 sql for show
				.seats(Arrays.asList(Seat.builder().type("gold").build(), Seat.builder().type("silver").build()))//2 sql query + 2 sql for show_seats table for mapping
				.build();
		showDao.save(show);//total 1 + 2 + 2 = 5 sql query

		// now add @JoinColumn(name = "show_id") below oneToMany mapping

//		show.getSeats().remove(0);
//		showDao.save(show);

		final Seat gold = Seat.builder().type("gold2").build();
		final Seat silver = Seat.builder().type("silver2").build();
		final Show show2 = Show.builder().name("show 012")//1 sql for show
				.seats(Arrays.asList(gold, silver))//2 sql query + 2 sql for show_seats table for mapping
				.build();
		gold.setShow(show2);
		silver.setShow(show2);
		showDao.save(show2);//total 1 + 2 + 2 = 5 sql query

		//todo : working : fetch show and iterate seats
		final List<Show> shows = showDao.findAll();
		shows.forEach(s -> {
			final List<Seat> seats = s.getSeats();
			System.out.println("seats = " + seats);
		});
		//todo : fetch seats and iterate shows
		final List<Seat> seats = seatDao.findAll();
		seats.forEach(s -> {
			final Show show1 = s.getShow();
			System.out.println("show1 = " + show1);
		});

		//bi directional : my old uni directional working + add bwlow 2 things 1st add mapped by in 1:M side
		//and 2nd add new field in other side with annotation manyToOne and (fetch = eager) this is optional
//		1st
//		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//old
//		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "show")//new

//		2nd
//		@ManyToOne Show show;//m1
//		@ManyToOne(fetch = FetchType.EAGER) Show show;//m2

		//todo : change string data type length varchar length , and check in h2 and mysql
		showDao.save(Show.builder().name("123456789012345678901").build());


	}
}
