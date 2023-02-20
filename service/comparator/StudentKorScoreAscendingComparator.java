package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentKorScoreAscendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return Integer.compare(o1.getKorScore(), o2.getKorScore());
	}
}
