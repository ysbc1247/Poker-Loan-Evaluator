package com.projects.poker.controller;


import com.projects.poker.service.CardRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rank")
public class CardRankController {
    @Autowired
    private CardRankService cardRankService;
    @GetMapping
    public int getCardRank(@RequestParam String card1, @RequestParam String card2){
        return cardRankService.cardRank(card1, card2);
    }
}
