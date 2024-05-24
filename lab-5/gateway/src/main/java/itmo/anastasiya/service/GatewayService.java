package itmo.anastasiya.service;

import itmo.anastasiya.CatDto;

import java.util.List;

public interface GatewayService {
    CatDto getCat(Long id);

    List<CatDto> getAllCats();

    List<CatDto> getCatsFriends(Long id);

    List<CatDto> filter(String color, String breed);

}
