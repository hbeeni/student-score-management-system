package main;

import controller.StudentManagementConsole;

public class Program {
	public static void main(String[] args) {
		StudentManagementConsole console = new StudentManagementConsole();

		int menu;
		boolean keepLoop = true;

		while (keepLoop) {
			menu = console.inputMenu();

			switch (menu) {
			case 1:
				console.inputInfo();
				break;
			case 2:
				console.printInfo();
				break;
			case 3:
				console.updateScores();
				break;
			case 4:
				console.deleteInfo();
				break;
			case 5:
				keepLoop = false;
				System.out.println();
				System.out.println("학생 성적 관리 프로그램을 종료합니다.");
				break;
			default:
				System.out.println("※ 메뉴를 잘못 선택하셨습니다. ※");
				System.out.println("매뉴는 1번에서 5번까지입니다.");
				System.out.println();
				System.out.println("다시 입력해주세요.");
				break;
			}
		}
	}
}
