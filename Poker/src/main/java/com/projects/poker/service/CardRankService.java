package com.projects.poker.service;

import org.springframework.stereotype.Service;

@Service
public class CardRankService {

    String[] handRanks = {
            "A-A", "K-K", "Q-Q", "J-J", "A-Ks", "T-T", "A-Qs", "9-9", "A-Js", "K-Qs",
            "8-8", "A-Ts", "A-Q", "7-7", "K-Js", "6-6", "A-9s", "A-J", "K-Ts",
            "5-5", "A-8s", "A-T", "K-Q", "4-4", "A-7s", "A-2s", "A-3s", "A-6s",
            "A-5s", "A-9", "A-4s", "K-J", "K-9s", "K-T", "A-8", "Q-Js", "Q-Q",
            "J-Ts", "A-7", "K-8s", "K-Qs", "A-6", "K-Js", "Q-Ts", "A-5", "A-4",
            "A-3", "A-2", "K-Ts", "J-J", "Q-J", "K-9", "J-T", "T-T", "K-7s",
            "K-8", "9-9", "Q-9s", "Q-Q", "K-6s", "J-9s", "Q-Js", "8-8", "K-5s",
            "K-7", "7-7", "J-Ts", "Q-T", "K-4s", "K-6", "6-6", "Q-8s", "J-8s",
            "K-3s", "K-5", "T-9s", "J-9", "K-4", "Q-9", "T-T", "K-2s", "5-5",
            "Q-7s", "K-3", "J-7s", "K-2", "4-4", "Q-J", "Q-6s", "J-8", "Q-5s",
            "Q-8", "9-9", "Q-4s", "J-9s", "Q-3s", "Q-7", "8-8", "Q-2s", "J-T",
            "Q-6", "7-7", "Q-5", "Q-4", "Q-3", "Q-2", "J-8s", "6-6", "T-9",
            "J-7", "9-8s", "5-5", "J-6s", "J-5s", "J-4s", "J-3s", "J-2s", "T-T",
            "8-8", "J-6", "9-8", "T-8s", "4-4", "9-7s", "8-7s", "J-5", "J-4",
            "J-3", "J-2", "T-9s", "T-8", "7-7", "T-7s", "6-6", "9-6s", "T-6s",
            "8-7", "T-5s", "5-5", "9-5s", "T-4s", "9-7", "T-3s", "T-2s",
            "T-7", "4-4", "9-4s", "8-6s", "9-6", "T-6", "8-5s", "7-6s", "T-5",
            "6-5s", "9-3s", "8-4s", "9-2s", "T-4", "8-3s", "7-5s", "T-3", "8-2s",
            "7-4s", "9-5", "6-4s", "9-4", "7-3s", "9-3", "6-3s", "9-2", "8-5",
            "7-2s", "8-4", "6-5", "8-3", "5-4s", "8-2", "7-5", "6-4", "7-4", "5-3s",
            "7-3", "6-3", "7-2", "6-2s", "6-4", "6-2", "5-4", "5-3", "5-2s", "5-2"
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
