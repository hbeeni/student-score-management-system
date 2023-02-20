package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentSciScoreDescendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return Integer.compare(o2.getSciScore(), o1.getSciScore());
	}
}
