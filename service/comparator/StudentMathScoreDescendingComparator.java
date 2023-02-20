package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentMathScoreDescendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return Integer.compare(o2.getMathScore(), o1.getMathScore());
	}
}
