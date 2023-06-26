package com.example.likelion.week11day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AdjacentMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // StringTokenizer : 입력받은 문자열을 띄어쓰기 ' ' 또는 delimiter를 기준으로 나누어서
        // 한 단어씩 반환해줌
        StringTokenizer graphToken = new StringTokenizer(br.readLine());


        // nextToken() : 다음 단어를 가져오기
        int maxNode = Integer.parseInt(graphToken.nextToken()); // 8
        int edges = Integer.parseInt(graphToken.nextToken()); // 10


        // 인접행렬 : 2차원 배열
        int[][] graph = new int[maxNode][maxNode];

        // 간선의 갯수만큼 반복해서 입력을받는다
        for (int i = 0; i < edges; i++) {
            StringTokenizer edgeToken = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(edgeToken.nextToken()); // 0
            int end = Integer.parseInt(edgeToken.nextToken()); // 1

            // 유향 그래프면 아래코드
            graph[start][end] = 1;

            // 무향 그래프면 아래코드도 함께 (양방향)
            graph[end][start] = 1;
        }

        for (int[] row : graph) {
            System.out.println(Arrays.toString(row));
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
