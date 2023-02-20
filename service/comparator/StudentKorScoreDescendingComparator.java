package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentKorScoreDescendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return Integer.compare(o2.getKorScore(), o1.getKorScore());
	}
}
