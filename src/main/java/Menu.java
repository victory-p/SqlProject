import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    BufferedReader br;

    public String printMenu() throws IOException {
        System.out.println("\n==========MENU==========");
        System.out.println("[1] 팀원 명단 조회하기");
        System.out.println("[2] 팀원 정보 추가하기");
        System.out.println("[3] 팀원 정보 수정하기");
        System.out.println("[4] 팀원 정보 삭제하기");
        System.out.println("[5] 팀원 이름 검색하기");
        System.out.println("[6] 팀원 명단 파일 저장하기");
        System.out.println("[0] 종료");
        System.out.println("========================");

        br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
