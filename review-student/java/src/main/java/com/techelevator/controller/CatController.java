package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }
    @RequestMapping(path = "/api/cards/random", method = RequestMethod.GET)
    public CatCard getRandomCard(){
        CatPic pic = catPicService.getPic();
        CatFact fact = catFactService.getFact();

        CatCard card = new CatCard();
        card.setCatFact(fact.getText());
        card.setImgUrl(pic.getFile());

        return card;
    }
    @ResponseStatus (HttpStatus.CREATED)
    @RequestMapping(path = "/api/cards", method = RequestMethod.POST)
    public boolean create(@Valid @RequestBody CatCard catCard){
        return catCardDao.save(catCard);
    }

    @RequestMapping (path = "/api/cards", method = RequestMethod.GET)
    public List<CatCard> listAll (){
        return catCardDao.list();
    }

    @RequestMapping (path = "/api/cards/{id}", method = RequestMethod.GET)
    public CatCard get(@PathVariable long id){
        return catCardDao.get(id);
    }

    @RequestMapping (path = "/api/cards/{id}", method = RequestMethod.PUT)
    public boolean update (@PathVariable long id, @Valid @RequestBody CatCard changedCard){
        catCardDao.get(id);
        return catCardDao.update(id, changedCard);
    }
    @ResponseStatus (HttpStatus.NO_CONTENT)

    @RequestMapping (path = "/api/cards/{id}", method = RequestMethod.DELETE)
    public boolean delete (@PathVariable long id){
        catCardDao.get(id);
        return catCardDao.delete(id);
    }

}
