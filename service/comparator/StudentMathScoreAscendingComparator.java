package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentMathScoreAscendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return Integer.compare(o1.getMathScore(), o2.getMathScore());
	}
}
