package com.projects.poker.service;

import org.springframework.stereotype.Service;

@Service
public class CardRankService {

    String[] handRanks = {
            "A-A", "K-K", "Q-Q", "J-J", "A-Ks", "T-T", "A-K", "A-Qs",
            "9-9", "A-Js", "K-Qs", "A-Ts", "A-Q", "8-8", "A-J", "K-Js",
            "Q-Js", "7-7", "A-9s", "K-Ts", "Q-Ts", "A-8s", "K-Q", "6-6",
            "A-7s", "A-5s", "A-9", "A-6s", "A-4s", "A-3s", "A-2s", "K-J",
            "Q-J", "K-9s", "J-Ts", "K-T", "5-5", "K-8s", "Q-9s", "J-9s",
            "4-4", "K-7s", "Q-T", "J-T", "K-6s", "K-5s", "K-4s", "K-3s",
            "K-2s", "Q-8s", "J-8s", "9-8s", "Q-7s", "3-3", "Q-6s", "J-7s",
            "Q-5s", "Q-4s", "Q-3s", "Q-2s", "J-9", "T-9s", "J-6s", "J-5s",
            "J-4s", "J-3s", "J-2s", "T-8s", "9-7s", "T-9", "8-7s", "T-7s",
            "9-6s", "T-6s", "9-5s", "T-8", "8-6s", "T-5s", "9-4s", "T-4s",
            "9-3s", "T-3s", "9-2s", "T-7", "8-5s", "T-6", "8-4s", "T-2s",
            "8-3s", "8-2s", "9-8", "7-6s", "9-7", "7-5s", "9-6", "7-4s",
            "9-5", "6-5s", "9-4", "7-3s", "9-3", "6-4s", "9-2", "7-2s",
            "8-7", "6-3s", "8-6", "6-2s", "8-5", "5-4s", "8-4", "7-6",
            "8-3", "5-3s", "8-2", "7-5", "6-5", "7-4", "5-2s", "7-3",
            "6-4", "7-2", "6-3", "6-2", "5-4", "5-3", "5-2"
    };
    public int cardRank(String card1, String card2, Boolean suited) {
        int rank = -1;
        String hand = card1 + "-" + card2;
        if(suited){
            hand += 's';
        }
        for (int i = 0; i < handRanks.length; i++) {
            if (handRanks[i].equals(hand)) {
                rank = i + 1;
                break;
            }
        }
        return rank;
    }
}
