package dto;

public class Student {
	private String id;
	private String name;
	private String gender;
	private int[] scores;

	public Student() {
		this.scores = new int[4];
	}

	private boolean validateId(String id) {
		IllegalArgumentException needTenNumbersException = new IllegalArgumentException("※ 학번은 10자리 숫자를 입력해야 합니다. ※");

		try {
			if (id.length() != 10 || Long.parseLong(id) < 0) { // 학번이 10자리가 안 되거나 음수일 때
				throw needTenNumbersException;
			}

			String yearOfIdString = id.substring(0, 4);
			int yearOfId = Integer.parseInt(yearOfIdString);

			// 앞자리 4자리 1950-2023까지 가능
			if (!(yearOfId >= 1950 && yearOfId <= 2023)) {
				throw new IllegalArgumentException("※ 학번의 앞 4자리는 1950과 2023 사이의 정수입니다. ※");
			}
		} catch (NumberFormatException ne) { // 정수가 아닐 때
			throw needTenNumbersException;
		}

		return true;
	}

	private boolean validateName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException("※ 이름을 정확하게 입력해주세요. ※");
		} else if (!name.matches("^[가-힣]*$")) {
			throw new IllegalArgumentException("※ 이름을 한글로 정확하게 입력해주세요. ※");
		}

		return true;
	}

	private boolean validateGender(String gender) {
		if (!(gender.equals("남") || gender.equals("여"))) {
			throw new IllegalArgumentException("※ 성별은 \"남\" 또는 \"여\"를 입력해주세요. ※");
		}

		return true;
	}

	private void validateScore(int score) {
		if (!(score >= 0 && score <= 100)) {
			throw new IllegalArgumentException("※ 점수 입력이 잘못되었습니다.\n(점수는 0부터 100 사이의 값입니다.) ※");
		}
	}

	public void setId(String id) {
		validateId(id);
		this.id = id;
	}

	public void setName(String name) {
		validateName(name);
		this.name = name;
	}

	public void setGender(String gender) {
		validateGender(gender);
		this.gender = gender;
	}

	public void setScores(int kor, int math, int eng, int sci) {
		setKorScore(kor);
		setMathScore(math);
		setEngScore(eng);
		setSciScore(sci);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public int[] getScores() {
		return scores;
	}

	public int getKorScore() {
		return this.scores[0];
	}

	public void setKorScore(int korScore) {
		this.validateScore(korScore);
		this.scores[0] = korScore;
	}

	public int getMathScore() {
		return this.scores[1];
	}

	public void setMathScore(int mathScore) {
		this.validateScore(mathScore);
		this.scores[1] = mathScore;
	}

	public int getEngScore() {
		return this.scores[2];
	}

	public void setEngScore(int engScore) {
		this.validateScore(engScore);
		this.scores[2] = engScore;
	}

	public int getSciScore() {
		return this.scores[3];
	}

	public void setSciScore(int sciScore) {
		this.validateScore(sciScore);
		this.scores[3] = sciScore;
	}

	@Override
	public String toString() {
		return String.format("%s\t %s\t %s\t %d\t %d\t %d\t %d", this.id, this.name, this.gender,
				this.scores[0], this.scores[1], this.scores[2], this.scores[3]);
	}
}
