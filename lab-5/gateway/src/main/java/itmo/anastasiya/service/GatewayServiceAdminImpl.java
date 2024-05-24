package itmo.anastasiya.service;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.OwnerDto;
import itmo.anastasiya.client.CatClientAdmin;
import itmo.anastasiya.client.OwnerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayServiceAdminImpl implements GatewayServiceAdmin {

    private final OwnerClient ownerClient;
    private final CatClientAdmin catClient;

    @Autowired
    public GatewayServiceAdminImpl(OwnerClient ownerClient, CatClientAdmin catClient) {
        this.ownerClient = ownerClient;
        this.catClient = catClient;
    }

    @Override
    public OwnerDto getOwner(Long id) {
        return ownerClient.getOne(id);
    }

    @Override
    public List<OwnerDto> allOwners() {
        return ownerClient.getAll();
    }

    @Override
    public CatDto getCatAdmin(Long id) {
        return catClient.getOneAdmin(id);
    }

    @Override
    public List<CatDto> getAllCatsAdmin() {
        return catClient.getAllAdmin();
    }

    @Override
    public List<CatDto> getCatsFriendsAdmin(Long id) {
        return catClient.getCatsFriendsAdmin(id);
    }

    @Override
    public List<CatDto> filterAdmin(String color, String breed) {
        List<CatDto> cats;
        cats = catClient.filterBreedAndColorAdmin(color, breed);
        return cats;
    }
}
