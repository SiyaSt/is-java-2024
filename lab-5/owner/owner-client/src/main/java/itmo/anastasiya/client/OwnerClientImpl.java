package itmo.anastasiya.client;

import itmo.anastasiya.OwnerDto;
import itmo.anastasiya.OwnerListDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OwnerClientImpl implements OwnerClient {
    @Value("${upload.owner.path}")
    private String baseUrl;

    @Override
    public OwnerDto getOne(Long id) {
        var restTemplate = new RestTemplate();
        var resourceUrl = baseUrl + "/api/owners/%s".formatted(id);
        return restTemplate.getForObject(resourceUrl, OwnerDto.class);
    }

    @Override
    public List<OwnerDto> getAll() {
        var restTemplate = new RestTemplate();
        var resourceUrl = baseUrl + "/api/owners";
        var response = restTemplate.getForObject(resourceUrl, OwnerListDto.class);
        return response != null ? response.owners() : null;
    }
}
