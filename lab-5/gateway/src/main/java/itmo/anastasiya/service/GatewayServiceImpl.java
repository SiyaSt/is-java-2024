package itmo.anastasiya.service;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.client.CatClient;
import itmo.anastasiya.user.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayServiceImpl implements GatewayService {
    private final CatClient catClient;

    @Autowired
    public GatewayServiceImpl(CatClient catClient) {
        this.catClient = catClient;
    }

    @Override
    public CatDto getCat(Long id) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail principal = (MyUserDetail) user;
        return catClient.getOne(id, principal.getOwner());
    }

    @Override
    public List<CatDto> getAllCats() {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail principal = (MyUserDetail) user;
        return catClient.getAll(principal.getOwner());
    }

    @Override
    public List<CatDto> getCatsFriends(Long id) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail principal = (MyUserDetail) user;
        return catClient.getCatsFriends(id, principal.getOwner());
    }

    @Override
    public List<CatDto> filter(String color, String breed) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUserDetail principal = (MyUserDetail) user;
        List<CatDto> cats;
        cats = catClient.filterBreedAndColor(color, breed, principal.getOwner());
        return cats;
    }
}
