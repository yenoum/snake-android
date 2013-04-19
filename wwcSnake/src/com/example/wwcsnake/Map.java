package com.example.wwcsnake;

public class Map {
	public int map1[][];
	public int map2[][];
	public int[][] getMap(int choice){
		if(choice == 0){
			map1 = new int[160][160];
			for(int i = 0;i<160;i++)
				for(int j=0;j<160;j++){
					if(i==0||j==0||i==159||j==159)
						map1[i][j]=1;
					else
						map1[i][j]=0;
				}
			return map1;
		}
		else return map1;
	}
}