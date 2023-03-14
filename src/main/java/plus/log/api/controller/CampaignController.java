package plus.log.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import plus.log.api.parameter.RequestParameter;
import plus.log.api.service.CampaignService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@RestController
@CrossOrigin(origins = {"http://172.29.3.60:3000/", "http://localhost:3000/"})
public class CampaignController {
    @Autowired
    CampaignService campaignService;

    @PostMapping(value = "/v1/campaign/headers")
    public ResponseEntity<Object> getCampaignListByCriteria(@RequestBody RequestParameter requestParameter) throws ParseException {

            var campaignListDto = campaignService.findCampaignListByCriteria(requestParameter);

            return ResponseEntity.ok().body(campaignListDto);

        }

}
