package io.hari.hexagonalproject.service1.adapter.jpa_adapter.feature1;

import io.hari.hexagonalproject.service1.code_domain_ports.Service1Domain;
import io.hari.hexagonalproject.service1.code_domain_ports.Service1DomainRepo_PORT;

public class Service1DomainDTO_RepoImpl_ADAPTER_Correct implements Service1DomainRepo_PORT {
    //0. from outside this call will talk:
    //0.1: Relationship of this class : "ADAPTER" class -impl-> "Domain Repo PORT"
    //1. this class will impl "domain repo port" class not the "domain dto" or "domain dto repo" class
    //2. we will use "domain dto repo" class to complete this ADAPTER class
    //3. outside world will send request as domain obj not the domain dto object
    //4. APPROACH : input domain object -> convert to domain dto object -> using domain dto repo class save to DB
    //5.

    //convert : Domain entity -> DomainDTO entity -> save DomainDTO to DB
    //OPTIONAL : we can add converter class

    Service1DomainDTO_RepoImpl dtoRepo;

    @Override
    public void save(Service1Domain entity) {

    }

    @Override
    public void findAll() {

    }

    //these override methods come from : domain repo(i.e. PORT) -> to domain dto repo impl (i.e. DTO Repo) -> to adapter (i.e. here in ADAPTER) -> and adapter will impl PORT methods using DTO Repo object
//    @Override
//    public void save(Service1DomainDTO entity) {
//        //use above dto repo obj and implement methods
//    }
//
//    @Override
//    public void findAll() {
//
//    }
}
