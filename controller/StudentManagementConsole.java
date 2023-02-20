package controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import dto.Student;
import service.StudentManager;
import service.comparator.StudentEngScoreAscendingComparator;
import service.comparator.StudentEngScoreDescendingComparator;
import service.comparator.StudentGenderAscendingComparator;
import service.comparator.StudentGenderDescendingComparator;
import service.comparator.StudentIdAscendingComparator;
import service.comparator.StudentIdDescendingComparator;
import service.comparator.StudentKorScoreAscendingComparator;
import service.comparator.StudentKorScoreDescendingComparator;
import service.comparator.StudentMathScoreAscendingComparator;
import service.comparator.StudentMathScoreDescendingComparator;
import service.comparator.StudentNameAscendingComparator;
import service.comparator.StudentNameDescendingComparator;
import service.comparator.StudentSciScoreAscendingComparator;
import service.comparator.StudentSciScoreDescendingComparator;

public class StudentManagementConsole {
	private StudentManager manager;

	public StudentManagementConsole() {
		manager = new StudentManager();
	}

	public int inputMenu() {
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

	public void inputInfo() {
		Scanner sc = new Scanner(System.in);
		Student student = null;

		System.out.println();
		System.out.println("======================================================================");
		System.out.println("[ 학생 정보 입력란 ]");
		System.out.println();

		while (true) {
			System.out.println("[학번 이름 성별 국어점수 수학점수 영어점수 과학점수] 순으로 입력해주세요.");
			System.out.println("예) 2016116426 박혜빈 여 100 100 100 100\n");
			System.out.print(">>> ");

			try {
				student = new Student();

				student.setId(sc.next());
				student.setName(sc.next());
				student.setGender(sc.next());

				int kor = sc.nextInt();
				int math = sc.nextInt();
				int eng = sc.nextInt();
				int sci = sc.nextInt();

				student.setScores(kor, math, eng, sci);

				break;
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println();
				System.out.print("----------------------------------------------------------------------");
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("※ 점수 입력이 잘못되었습니다.");
				System.out.println("(점수는 0부터 100 사이의 값입니다.) ※");
				System.out.println();
				System.out.print("----------------------------------------------------------------------");
			} finally {
				sc.nextLine();
			}
		}

		boolean isInserted = manager.InsertStudent(student);

		if (!isInserted) { // 학생 정보 입력 실패
			System.out.println();
			System.out.println("이미 시스템에 존재하는 학생입니다.");

			student = manager.searchStudent(student.getId());
			System.out.println("" + student.getId() + " " + student.getName() + " 학생의 정보를 조회하시겠습니까?");

			System.out.println();
			System.out.println("(1) 네 (2) 아니오");
			System.out.print(">>> ");

			int reply;
			boolean keepLoop = true;

			while (keepLoop) {
				try {
					reply = sc.nextInt();

					switch (reply) {
					case 1:
						System.out.println();
						System.out.println();
						System.out.println("학번\t\t 이름\t 성별\t 국어점수\t 수학점수\t 영어점수\t 과학점수");
						System.out.println("----------------------------------------------------------------------");
						System.out.println(student);

						keepLoop = false;
						break;
					case 2:
						keepLoop = false;
						break;
					default:
						System.out.println();
						System.out.println("(1) 네 (2) 아니오 중 선택해주세요");
						System.out.print(">>> ");
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println();
					System.out.println("(1) 네 (2) 아니오 중 선택해주세요");
					System.out.print(">>> ");
					sc.nextLine();
				}
			}
		} else { // 학생 정보 입력 성공
			System.out.println();
			System.out.println("==> " + student.getId() + " " + student.getName() + " 학생의 정보가 성공적으로 입력되었습니다.");
		}

//		subMenu("학생 정보 입력");
	}

	public void printInfo() {
		Scanner sc = new Scanner(System.in);
		String errorMessage = "※ 메뉴를 잘못 선택하셨습니다. ※"
				+ "\n매뉴는 1번에서 2번까지입니다."
				+ "\n\n다시 입력해주세요.\n"
				+ ">>> ";

		while (true) {
			System.out.println();
			System.out.println("======================================================================");
//			System.out.println("(1) 개별 학생 조회 (2) 전체 학생 조회 (3) 현재 메뉴 종료 (4) 시스템 종료");
			System.out.println("(1) 개별 학생 조회 (2) 전체 학생 조회");
			System.out.print(">>> ");

			int menu;
			boolean keepLoop = true;

			while (keepLoop) {
				try {
					menu = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println();
					System.out.print(errorMessage);
					sc.next();
					continue;
				}

				System.out.println();

				switch (menu) {
				case 1:
					System.out.println("======================================================================");
					System.out.println("정보를 조회할 학생의 학번을 입력해주세요");
					System.out.print(">>> ");

					while (true) {
						String id = sc.next();

						try {
							Student student = manager.searchStudent(id);

							if (student == null) {
								System.out.println();
								System.out.println("시스템에 존재하지 않는 학생의 학번입니다.");
							} else {
								System.out.println();
								System.out.println();
								System.out.println("학번\t\t 이름\t 성별\t 국어점수\t 수학점수\t 영어점수\t 과학점수");
								System.out.println("----------------------------------------------------------------------");
								System.out.println(student);
							}

							keepLoop = false;
							break;
						} catch (IllegalArgumentException e) {
							System.out.println();
							System.out.println();
							System.out.println(e.getMessage());
							System.out.println("다시 입력해주세요.");
							System.out.println(">>> ");
						}
					}

					break;
				case 2:
					if (manager.isEmptyStudentsList()) {
						System.out.println("조회할 학생 정보가 없어 홈으로 돌아갑니다.");
						return;
					}

					System.out.println();
					System.out.println("======================================================================");
					System.out.println("어떤 기준으로 정렬할까요?");
					System.out.println("(1) 입력순\t(2) 학번순\t(3) 이름순\t(4) 성별순\n(5) 국어점수순\t(6) 수학점수순\t(7) 영어점수순\t(8) 과학점수순");
					System.out.print(">>> ");

					int arrangeType;

					while (true) { // 정렬 타입 입력
						try {
							arrangeType = sc.nextInt();

							if (arrangeType >= 1 && arrangeType <= 8) {
								break;
							}

							System.out.println();
							System.out.print("※ 메뉴를 잘못 선택하셨습니다. ※"
									+ "\n매뉴는 1번에서 8번까지입니다.\n"
									+ ">>> ");
						} catch (InputMismatchException e) {
							System.out.println();
							System.out.print("※ 메뉴를 잘못 선택하셨습니다. ※"
									+ "\n매뉴는 1번에서 8번까지입니다.\n"
									+ ">>> ");
							sc.nextLine();
						}
					}

					System.out.println();
					System.out.println("(1) 오름차순 (2) 내림차순");
					System.out.print(">>> ");

					int order;

					while (true) { // 오름차순, 내림차순 입력
						try {
							order = sc.nextInt();

							if (order >= 1 && order <= 2) {
								break;
							}

							System.out.println();
							System.out.print("※ 메뉴를 잘못 선택하셨습니다. ※"
									+ "\n매뉴는 1번에서 2번까지입니다.\n"
									+ ">>> ");
						} catch (InputMismatchException e) {
							System.out.println();
							System.out.print("※ 메뉴를 잘못 선택하셨습니다. ※"
									+ "\n매뉴는 1번에서 2번까지입니다.\n"
									+ ">>> ");
							sc.nextLine();
						}
					}

					List<Student> students = manager.searchAllStudents();
					List<Student> studentsListForArrangement = (List<Student>) ((LinkedList) students).clone();

					System.out.println("\n\n전체 학생을 조회 중입니다...\n");
					System.out.println("학번\t\t 이름\t 성별\t 국어점수\t 수학점수\t 영어점수\t 과학점수");
					System.out.println("----------------------------------------------------------------------");

					if (arrangeType == 1) {
						if (order == 1) {
							for (Student student : studentsListForArrangement)
								System.out.println(student);
						} else {
							ListIterator<Student> lit = students.listIterator();

							while (lit.hasNext()) {
								lit.next();
							}

							while (lit.hasPrevious()) {
								System.out.println(lit.previous());
							}
						}
					} else {
						sortStudentsList(studentsListForArrangement, arrangeType, order);

						for (Student student : studentsListForArrangement)
							System.out.println(student);
					}

					System.out.println();
					System.out.println();
					System.out.println("전체 학생 정보 조회가 끝나 홈으로 돌아갑니다.");
					keepLoop = false;
					break;
				default:
					System.out.println();
					System.out.print(errorMessage);
					break;
				}
			}

			break;
		}

	}

	List<Student> sortStudentsList(List<Student> studentsList, int arrangeType, int order) {
		Comparator[][] comparatorArray = { { new StudentIdAscendingComparator(), new StudentIdDescendingComparator() },
				{ new StudentNameAscendingComparator(), new StudentNameDescendingComparator() },
				{ new StudentGenderAscendingComparator(), new StudentGenderDescendingComparator() },
				{ new StudentKorScoreAscendingComparator(), new StudentKorScoreDescendingComparator() },
				{ new StudentMathScoreAscendingComparator(), new StudentMathScoreDescendingComparator() },
				{ new StudentEngScoreAscendingComparator(), new StudentEngScoreDescendingComparator() },
				{ new StudentSciScoreAscendingComparator(), new StudentSciScoreDescendingComparator() },
		};

		Collections.sort(studentsList, comparatorArray[arrangeType - 2][order - 1]);
		return studentsList;
	}

	public void updateScores() {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("======================================================================");
		System.out.println("성적을 변경할 학생의 학번을 입력해주세요.");
		System.out.print(">>> ");

		String id;

		while (true) {
			try {
				id = sc.next();

				Student temp = new Student();
				temp.setId(id); // id를 유효하게 입력했는지 확인

				break;
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println();
				System.out.println("다시 입력해주세요.");
				System.out.print(">>> ");
			}
		}

		Student student = manager.searchStudent(id);

		if (student == null) {
			System.out.println();
			System.out.println("※ 시스템에 존재하지 않는 학생입니다. ※");

			return;
		}

		int[] scores = student.getScores();

		System.out.println();
		System.out.println(student.getId() + " " + student.getName() + " 학생의 현재 성적입니다.");
		System.out.printf("\n%s\t%s\t%s\t%s\n", "[국어]", "[수학]", "[영어]", "[과학]");
		System.out.printf("%d\t%d\t%d\t%d\n", scores[0], scores[1], scores[2], scores[3]);

		System.out.println();
		System.out.println("무슨 과목의 성적을 변경하시겠습니까?");
		System.out.println("1) 국어  2) 수학  3) 영어  4) 과학");
		System.out.print(">>> ");

		int subject, changedScore;

		while (true) {
			String errorMessage = "※ 메뉴를 잘못 선택하셨습니다. ※\n"
					+ "매뉴는 1번에서 4번까지입니다.\n"
					+ ">>> ";
			try {
				subject = sc.nextInt();

				if (subject >= 1 && subject <= 4) {
					break;
				}

				System.out.println();
				System.out.print(errorMessage);

			} catch (InputMismatchException e) {
				System.out.println();
				System.out.print(errorMessage);
				sc.nextLine();
			}
		}

		System.out.println();
		System.out.println("몇 점으로 변경하시겠습니까?");
		System.out.print(">>> ");

		boolean isChanged = false;

		while (true) {
			try {
				changedScore = sc.nextInt();
				isChanged = manager.changeScore(student, subject, changedScore);

				break;
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("※ 점수는 정수로 입력해주세요. ※");
				System.out.println();
				System.out.println("다시 입력해주세요.");
				System.out.print(">>> ");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				System.out.println();
				System.out.println(e.getMessage());
				System.out.println("다시 입력해주세요.");
				System.out.print(">>> ");
			}
		}

		System.out.println();
		if (isChanged) {
			System.out.println("성적을 성공적으로 변경하였습니다.");
		} else {
			System.out.println("※ 성적을 변경하지 못하였습니다. ※");
		}
	}

	public void deleteInfo() {
		Scanner sc = new Scanner(System.in);
		String errorMessage = "※ 메뉴를 잘못 선택하셨습니다. ※\n"
				+ "매뉴는 1번, 2번입니다.\n"
				+ "\n다시 입력해주세요.\n"
				+ ">>> ";

		System.out.println();
		System.out.println("======================================================================");
//		System.out.println("(1) 개별 학생 정보 삭제 (2) 전체 학생 정보 삭제 (3) 현재 메뉴 종료 (4) 시스템 종료");
		System.out.println("(1) 개별 학생 정보 삭제 (2) 전체 학생 정보 삭제");
		System.out.print(">>> ");

		int menu;
		boolean keepLoop = true;

		while (keepLoop) {
			try {
				menu = sc.nextInt();

				switch (menu) {
				case 1:
					System.out.println();
					System.out.println("======================================================================");
					System.out.println("정보를 삭제할 학생의 학번을 입력해주세요.");
					System.out.print(">>> ");

					String id;

					while (true) {
						id = sc.next();

						try {
							Student temp = new Student();
							temp.setId(id); // id 입력이 유효한지 검사

							break;
						} catch (IllegalArgumentException e) {
							System.out.println();
							System.out.println();
							System.out.println(e.getMessage());
							System.out.println();
							System.out.println("다시 입력해주세요.");
							System.out.print(">>> ");
						}
					}

					boolean isDeleted = manager.deleteStudent(id);

					if (!isDeleted)
						System.out.println("※ 시스템에 존재하지 않는 학생입니다. ※");
					else
						System.out.println(id + " 학생의 정보를 성공적으로 삭제하였습니다.");

					keepLoop = false;
					break;

				case 2:
					System.out.println();
					if (manager.isEmptyStudentsList()) {
						System.out.println("시스템에 삭제할 학생 정보가 존재하지 않아 홈으로 돌아갑니다.");
					} else {
						System.out.println("전체 학생정보를 삭제하는 중입니다...");
						manager.deleteAllStudents();

						System.out.println();
						System.out.println("전체 학생 정보를 성공적으로 삭제하여 홈으로 돌아갑니다.");
					}

					keepLoop = false;
					break;

				default:
					System.out.println();
					System.out.print(errorMessage);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.print(errorMessage);
				sc.next();
			}
		}
	}
}
