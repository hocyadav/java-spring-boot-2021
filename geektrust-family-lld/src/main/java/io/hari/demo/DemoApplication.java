package io.hari.demo;

import io.hari.demo.config.AppConfig;
import io.hari.demo.entity.Gender;
import io.hari.demo.entity.Node;
import io.hari.demo.entity.ParentNode;
import io.hari.demo.service.FamilyTreeDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	AppConfig config;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("config = " + config);
		FamilyTreeDS familyTree = new FamilyTreeDS();
		final Node queen = Node.builder().name("queen angra").gender(Gender.female).build();
		final Node king = Node.builder().name("king shan").gender(Gender.male).spouse(queen).build();
		queen.setSpouse(king);
		familyTree.root = king;
		familyTree.levelOrderTraversal();

		final Node chit = Node.builder().name("chit").gender(Gender.male).build();
		final Node vich = Node.builder().name("vich").gender(Gender.male).build();
		final Node aras = Node.builder().name("aras").gender(Gender.male).build();
		final Node satya = Node.builder().name("satya").gender(Gender.female).build();
		final List<Node> children = Arrays.asList(
				chit,
				Node.builder().name("ish").gender(Gender.male).build(),
				vich,
				aras,
				satya
		);
		familyTree.addChildrenToPapa(king, children);

		familyTree.levelOrderTraversal();
		Node amba = Node.builder().name("amba").gender(Gender.female).build();
		familyTree.addSpouse(chit, amba);

		Node lika = Node.builder().name("lika").gender(Gender.female).build();
		familyTree.addSpouse(vich, lika);
		Node chitra = Node.builder().name("chitra").gender(Gender.female).build();
		familyTree.addSpouse(aras, chitra);
		Node vyan = Node.builder().name("vyan").gender(Gender.male).build();
		familyTree.addSpouse(satya, vyan);
		familyTree.levelOrderTraversal();

		final ParentNode parentNode = familyTree.findParentNode_(king);
		System.out.println("parentNode = " + parentNode);

		final ParentNode parentNode1 = familyTree.findParentNode_(queen);
		System.out.println("parentNode1 = " + parentNode1);

		final ParentNode parentNode_ = familyTree.findParentNode_(chit);
		System.out.println("parentNode_ = " + parentNode_);

		final Node dritya = Node.builder().name("dritya").gender(Gender.female).build();
		final List<Node> chitChildren = Arrays.asList(
				dritya,
				Node.builder().name("tritha").gender(Gender.female).build(),
				Node.builder().name("vritha").gender(Gender.male).build());
		familyTree.searchNodeAndAddChildren(chit, chitChildren);
		familyTree.levelOrderTraversal();

		familyTree.addSpouse(dritya, Node.builder().name("jaya").gender(Gender.male).build());
		Node yodhan = Node.builder().name("yodhan").gender(Gender.male).build();
		familyTree.searchNodeAndAddChildren(dritya, Arrays.asList(yodhan));

		final List<Node> newChildren = Arrays.asList(
				Node.builder().name("vila").gender(Gender.female).build(),
				Node.builder().name("chika").gender(Gender.female).build()
		);
		familyTree.searchNodeAndAddChildren(vich, newChildren);

		final Node jnki = Node.builder().name("jnki").gender(Gender.female).build();
		final List<Node> newChildren1 = Arrays.asList(
				jnki,
				Node.builder().name("ahit").gender(Gender.male).build()
		);
		familyTree.searchNodeAndAddChildren(aras, newChildren1);

		familyTree.addSpouse(jnki, Node.builder().name("arit").gender(Gender.male).build());

		final List<Node> newChildren2 = Arrays.asList(
				Node.builder().name("laki").gender(Gender.male).build(),
				Node.builder().name("lavnya").gender(Gender.female).build()
		);
		familyTree.searchNodeAndAddChildren(jnki, newChildren2);

		final Node asva = Node.builder().name("asva").gender(Gender.male).build();
		final Node vyas = Node.builder().name("vyas").gender(Gender.male).build();
		final List<Node> newChildren3 = Arrays.asList(
				asva,
				vyas,
				Node.builder().name("atya").gender(Gender.female).build()
		);
		familyTree.searchNodeAndAddChildren(satya, newChildren3);


		final Node satvy = Node.builder().name("satvy").gender(Gender.female).build();
		familyTree.addSpouse(asva, satvy);
		final List<Node> newChildren4 = Arrays.asList(
				Node.builder().name("vasa").gender(Gender.male).build()
		);
		familyTree.searchNodeAndAddChildren(asva, newChildren4);

		final Node krpi = Node.builder().name("krpi").gender(Gender.female).build();
		familyTree.addSpouse(vyas, krpi);
		final List<Node> newChildren5 = Arrays.asList(
				Node.builder().name("kriya").gender(Gender.male).build(),
				Node.builder().name("krithi").gender(Gender.female).build()
		);
//		familyTree.searchNodeAndAddChildren(vyas, newChildren5);//working
		familyTree.searchNodeAndAddChildren(krpi, newChildren5);//working - added edge case for not connected spouse coz it doesnt have parent
		familyTree.levelOrderTraversal();

	}
}
