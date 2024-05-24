package itmo.anastasiya.client;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.CatListDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CatClientAdminImpl implements CatClientAdmin {
    @Value("${upload.cat.path}")
    private String baseUrl;

    public List<CatDto> getAllAdmin() {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String resourceUrl = baseUrl + "/api/cats/admin";
        CatListDto response = restTemplate.getForObject(resourceUrl, CatListDto.class);
        return response != null ? response.cats() : null;
    }

    public CatDto getOneAdmin(Long id) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String resourceUrl = baseUrl + "/api/cats/admin/%s".formatted(id);
        return restTemplate.getForObject(resourceUrl, CatDto.class);
    }

    public List<CatDto> getCatsFriendsAdmin(Long id) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String resourceUrl = baseUrl + "/api/cats/admin/%s".formatted(id) + "/friends";
        CatListDto response = restTemplate.getForObject(resourceUrl, CatListDto.class);
        return response != null ? response.cats() : null;
    }

    public List<CatDto> filterBreedAndColorAdmin(String breed, String color) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String resourceUrl = baseUrl;
        if (breed == null) {
            resourceUrl = resourceUrl + "/api/cats/admin/filter?color=%s".formatted(color);
        } else if (color == null) {
            resourceUrl = resourceUrl + "/api/cats/admin/filter?breed=%s".formatted(breed);
        } else {
            resourceUrl = resourceUrl + "/api/cats/admin/filter?color=%s&breed=%s".formatted(color, breed);
        }

        CatListDto response = restTemplate.getForObject(resourceUrl, CatListDto.class);
        return response != null ? response.cats() : null;
    }
}
