package com.example.likelion.week11day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class AdjacentList {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // StringTokenizer : 입력받은 문자열을 띄어쓰기 ' ' 또는 delimiter를 기준으로 나누어서
        // 한 단어씩 반환해줌
        StringTokenizer graphToken = new StringTokenizer(br.readLine());


        // nextToken() : 다음 단어를 가져오기
        int maxNode = Integer.parseInt(graphToken.nextToken()); // 8
        int edges = Integer.parseInt(graphToken.nextToken()); // 10

        // 안쪽의 List<Intger>가 maxNode의 길이를 반드시 가지지 않을것이다.
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < maxNode; i++) {
            adjList.add(new ArrayList<>());
        }


        // 간선의 갯수만큼 반복해서 입력을받는다
        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeToken = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(edgeToken.nextToken()); // 0
            int end = Integer.parseInt(edgeToken.nextToken()); // 1

            adjList.get(start).add(end);

            adjList.get(end).add(start);
        }


    }
}

/* 정점의 갯수, 간선의 갯수
8 10
0 1
0 2
0 3
1 3
1 4
2 5
3 4
4 7
5 6
6 7
 */
