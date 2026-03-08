import java.util.Arrays;

public class Student implements MyComparable<Student> {

    private String name;
    private int[] grades;

    public Student(String name, int... grades) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя студента не должно быть пустым");
        }
        this.name = name;
        setGrades(grades);
    }

    private void setGrades(int... grades) {
        if (grades == null) {
            this.grades = new int[0];
            return;
        }

        for (int grade : grades) {
            validateGrade(grade);
        }

        // защитное копирование
        this.grades = Arrays.copyOf(grades, grades.length);
    }

    private void validateGrade(int grade) {
        if (grade < 2 || grade > 5) {
            throw new IllegalArgumentException(
                    "Оценка должна быть в диапазоне 2..5. Некорректная оценка: " + grade
            );
        }
    }

    public String getName() {
        return name;
    }

    public int[] getGrades() {
        // возвращаем копию
        return Arrays.copyOf(grades, grades.length);
    }

    public void addGrade(int grade) {
        validateGrade(grade);

        int[] newGrades = Arrays.copyOf(grades, grades.length + 1);
        newGrades[newGrades.length - 1] = grade;
        grades = newGrades;
    }

    public double getAverageGrade() {
        if (grades.length == 0) return 0;

        int sum = 0;
        for (int grade : grades) sum += grade;
        return (double) sum / grades.length;
    }

    public boolean isExcellentStudent() {
        if (grades.length == 0) return false;

        for (int grade : grades) {
            if (grade != 5) return false;
        }
        return true;
    }

    @Override
    public int compare(Student other) {
        return Double.compare(this.getAverageGrade(), other.getAverageGrade());
    }

    @Override
    public String toString() {
        return name + ": " + Arrays.toString(grades);
    }
}
