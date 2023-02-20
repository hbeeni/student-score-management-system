package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentSciScoreAscendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return Integer.compare(o1.getSciScore(), o2.getSciScore());
	}
}
