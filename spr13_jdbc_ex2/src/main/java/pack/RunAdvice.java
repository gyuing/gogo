package pack;

import java.util.Scanner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RunAdvice {
	@Autowired
	private GogekInter gogekInter;

	@Around("execution(public void dataList())")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("AOP 시작");

		System.out.print("사번 : ");
		Scanner sc = new Scanner(System.in);
		String no = sc.nextLine();
		System.out.print("이름 : ");
		String name = sc.nextLine();

		for (SawonDto s : gogekInter.config(no, name)) {
			if (!no.equals(s.getSawon_no()) && !name.equals(s.getSawon_name())) {
				System.out.println("사원 인증실패!. 작업을 종료합니다.");
				return null;
			}
		}

		Object object = joinPoint.proceed();

		System.out.println("AOP 종료");
		return object;
	}
}
