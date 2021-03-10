package com.es.elasticsearchspringboot.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.es.elasticsearchspringboot.dao.CustomerDao;
import com.es.elasticsearchspringboot.entity.Customer;

/**
 * @author HariomYadav
 * @since 26/09/20
 */
@RequestMapping ("/api/v1/")
@RestController
public class CustomerResource {
    @Autowired
    CustomerDao customerDao;

    /* working, */
    @RequestMapping (method = RequestMethod.GET)
    public Optional<List<Customer>> getAllCustomer() {
        System.err.println("CustomerResource.getAllCustomer");

        final List<Customer> customerList = new ArrayList<>();
        customerDao.findAll().forEach(customerList::add);
        System.out.println("customerList = " + customerList);
        return Optional.of(customerList);
    }

    /* working */
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public Optional<Customer> getCustomer(@PathVariable ("id") Integer id) {
        System.err.println("CustomerResource.getCustomer");
        System.out.println("id = " + id);

        final Optional<Customer> customerDaoById = customerDao.findById(id);
        System.out.println("customerDaoById = " + customerDaoById);
        return customerDaoById;
    }

    /* working */
    @RequestMapping (value = "/name/{f_name}", method = RequestMethod.GET)
    public Optional<List<Customer>> findByFirstName(@PathVariable ("f_name") String firstName) {
        System.err.println("CustomerResource.findByFirstName");
        System.out.println("firstName = " + firstName);

        final List<Customer> customerList = customerDao.findByFirstName(firstName);
        System.out.println("customerList = " + customerList);
        return Optional.ofNullable(customerList);
    }

    /* working */
    @RequestMapping (value = "/name_page/{f_name}", method = RequestMethod.GET)
    public Optional<List<Customer>> findByFirstNamePagination(@PathVariable ("f_name") String firstName) {
        System.err.println("CustomerResource.findByFirstNamePagination");
        System.out.println("firstName = " + firstName);

        final List<Customer> customerList = customerDao.findByFirstName(firstName, PageRequest.of(0, 1));
        System.out.println("customerList = " + customerList);
        return Optional.ofNullable(customerList);
    }

    /* working */
    @RequestMapping (method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer) {
        System.err.println("CustomerResource.addCustomer");
        System.out.println("customer = " + customer);

        return customerDao.save(customer);
    }

    /* working */
    @RequestMapping (method = RequestMethod.DELETE)
    public void deleteCustomer() {
        System.err.println("CustomerResource.deleteCustomer");

        customerDao.deleteAll();
    }

}
