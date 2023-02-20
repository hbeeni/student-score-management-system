package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentIdAscendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getId().compareTo(o2.getId());
	}
}
