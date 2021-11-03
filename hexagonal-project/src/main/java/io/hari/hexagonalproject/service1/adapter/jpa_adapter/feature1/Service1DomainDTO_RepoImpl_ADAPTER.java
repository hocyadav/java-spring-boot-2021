package io.hari.hexagonalproject.service1.adapter.jpa_adapter.feature1;

public class Service1DomainDTO_RepoImpl_ADAPTER implements Service1DomainDTO_RepoImpl {//from outside this call will talk:
    //convert : Domain entity -> DomainDTO entity -> save DomainDTO to DB
    //OPTIONAL : we can add converter class

    Service1DomainDTO_RepoImpl dtoRepo;

    //these override methods come from : domain repo(i.e. PORT) -> to domain dto repo impl (i.e. DTO Repo) -> to adapter (i.e. here in ADAPTER) -> and adapter will impl PORT methods using DTO Repo object
    @Override
    public void save(Service1DomainDTO entity) {
        //use above dto repo obj and implement methods
    }

    @Override
    public void findAll() {

    }
}
