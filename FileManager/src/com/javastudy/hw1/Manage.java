package com.javastudy.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Manage {

	public static void main(String[] args) throws IOException {

		StringBuffer currentDir = new StringBuffer("D:\\JAVATEST");
		int turn=0;
		while (true) {
			// 当没有使用自带参数输入时我们需要从控制台再次输入参数
			if (args.length == 0||turn>0) {
				System.out.println("Please Input the Instruction:");
				args = new String[10];
				
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				String line=br.readLine();
				args = line.split("\\s+");
			}
			System.out.println("CURR--DIR:"+currentDir.toString());
			turn++;
			// //输出已经读入的参数
			// for(String s:args){
			// System.out.println(s);
			// }

			if (args[0].equals("exit")) {
				System.out.println("Thaks For Using Our System");
				break;
			}

			ManagerFactory managerFactory = new ManagerFactory();
			Manager manager1 = managerFactory.getManager(args[0]);
			manager1.excute(args, currentDir);

		}
	}
}
