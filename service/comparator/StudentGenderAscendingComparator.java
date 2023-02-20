package service.comparator;

import java.util.Comparator;

import dto.Student;

public class StudentGenderAscendingComparator implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getGender().compareTo(o2.getGender());
	}
}
