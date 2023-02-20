package main;

import java.util.Scanner;

import controller.StudentManagementConsole;

public class Program {
	public static void main(String[] args) {
		StudentManagementConsole console = new StudentManagementConsole();
		int menu;
		boolean keepLoop = true;

		while (keepLoop) {
			menu = inputMenu();

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

	private static int inputMenu() {
		Scanner scan = new Scanner(System.in);
		String errorMessage = "※ 메뉴를 잘못 선택하셨습니다. ※"
				+ "\n매뉴는 1번에서 5번까지입니다."
				+ "\n\n다시 입력해주세요.\n"
				+ ">>> ";
		int menu = 0;

		System.out.println();
		System.out.println("======================================================================");
		System.out.println("### 학생 성적 관리 프로그램 ###");
		System.out.println();
		System.out.println("1. 학생 정보 입력");
		System.out.println("2. 학생 정보 조회");
		System.out.println("3. 성적 변경");
		System.out.println("4. 학생 정보 삭제");
		System.out.println("5. 프로그램 종료");
		System.out.print(">>> ");

		while (true) {
			while (!scan.hasNextInt()) {
				System.out.print(errorMessage);
				scan.next();
			}

			menu = scan.nextInt();

			if (!(menu >= 1 && menu <= 5)) {
				System.out.println();
				System.out.print(errorMessage);
				continue;
			}

			break;
		}

		return menu;
	}
}
