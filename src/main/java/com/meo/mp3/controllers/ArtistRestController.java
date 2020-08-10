package com.meo.mp3.controllers;

import com.meo.mp3.models.artist.Artist;
import com.meo.mp3.services.impl.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/artist/")
public class ArtistRestController {
    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/list",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Artist> getList(){
        return artistService.findAll();
    }

    @RequestMapping(value = "/{id}/detail",method = RequestMethod.GET , produces = {MediaType.APPLICATION_JSON_VALUE})
    public Artist getById(@PathVariable("id") Long id){
        return artistService.findById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Artist save(@RequestBody Artist artist){
        return artistService.save(artist);
    }

    @RequestMapping(value = "/{id}/delete",method = RequestMethod.DELETE,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Artist delete(@PathVariable("id") Long id){
        return artistService.delete(id);
    }
}