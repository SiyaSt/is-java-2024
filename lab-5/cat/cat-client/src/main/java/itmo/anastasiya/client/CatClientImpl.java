package itmo.anastasiya.client;

import itmo.anastasiya.CatDto;
import itmo.anastasiya.CatListDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CatClientImpl implements CatClient {
    @Value("${upload.cat.path}")
    private String baseUrl;

    public List<CatDto> getAll(Long id) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String resourceUrl = baseUrl + "/api/cats?owner=%s".formatted(id);
        CatListDto response = restTemplate.getForObject(resourceUrl, CatListDto.class);
        return response != null ? response.cats() : null;
    }


    public CatDto getOne(Long id, Long ownerId) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String resourceUrl = baseUrl + "/api/cats/%s".formatted(id) + "?owner=%s".formatted(ownerId);
        return restTemplate.getForObject(resourceUrl, CatDto.class);
    }


    public List<CatDto> getCatsFriends(Long id, Long ownerId) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String resourceUrl = baseUrl + "/api/cats/%s".formatted(id) + "/friends?owner=%s".formatted(ownerId);
        CatListDto response = restTemplate.getForObject(resourceUrl, CatListDto.class);
        return response != null ? response.cats() : null;
    }

    public List<CatDto> filterBreedAndColor(String color, String breed, Long ownerId) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String resourceUrl = baseUrl;
        if (breed == null) {
            resourceUrl = resourceUrl + "/api/cats/filter?owner=%s&".formatted(ownerId) + "color=%s".formatted(color);
        } else if (color == null) {
            resourceUrl = resourceUrl + "/api/cats/filter?owner=%s&".formatted(ownerId) + "breed=%s".formatted(breed);
        } else {
            resourceUrl = resourceUrl
                    + "/api/cats/filter?owner=%s&".formatted(ownerId)
                    + "color=%s&breed=%s".formatted(color, breed);
        }

        CatListDto response = restTemplate.getForObject(resourceUrl, CatListDto.class);
        return response != null ? response.cats() : null;
    }
}
