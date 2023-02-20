package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentIdDescendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return o2.getId().compareTo(o1.getId());
	}
}
