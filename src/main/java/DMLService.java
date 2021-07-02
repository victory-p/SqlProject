import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DMLService {
    final String INSERT_SQL = "INSERT INTO PERSON ( NAME, GENDER, MAJOR, STUDENT_NUM, REG_DATE) VALUES ( ?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE PERSON SET NAME = ?, GENDER = ?, MAJOR = ?, STUDENT_NUM = ? WHERE ID = ?";
    final String DELETE_SQL = "DELETE FROM PERSON WHERE ID = ? ";

    Connection conn;
    PreparedStatement pstmt;

    public DMLService(Connection conn) {
        this.conn = conn;
    }

    // 데이터 삽입 함수
    public int insertPerson(HashMap<String, Object> dataMap) throws SQLException {

        int inserted = 0;

        try {
            // PreparedStatement 생성
            pstmt = conn.prepareStatement(INSERT_SQL);

            // 입력 데이터 매핑
            pstmt.setObject(1, dataMap.get("NAME"));
            pstmt.setObject(2, dataMap.get("GENDER"));
            pstmt.setObject(3, dataMap.get("MAJOR"));
            pstmt.setObject(4, dataMap.get("STUDENT_NUM"));
            pstmt.setObject(5, dataMap.get("REG_DATE"));

            // 쿼리 실행
            pstmt.executeUpdate();

            // 입력건수  조회
            inserted = pstmt.getUpdateCount();

            // 트랜잭션 COMMIT
            conn.commit();

        } catch (SQLException e) {
            // 오류출력
            System.out.println(e.getMessage());
            // 오류
            inserted = -1;
            // 트랜잭션 ROLLBACK
            if( conn != null ) {
                conn.rollback();
            }
        } finally {
            // PreparedStatement 종료
            if( pstmt != null ) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 결과 반환  - 입력된 데이터 건수
        return inserted;
    }

    // 데이터 수정 함수
    public int updatePerson(Map<String, Object> updateMap) throws SQLException {

        //   - 수정 결과 변수
        int updated = 0;

        try {
            // PreparedStatement 생성
            pstmt = conn.prepareStatement(UPDATE_SQL);

            // 입력 데이터 매핑
            pstmt.setObject(1, updateMap.get("NAME"));
            pstmt.setObject(2, updateMap.get("GENDER"));
            pstmt.setObject(3, updateMap.get("MAJOR"));
            pstmt.setObject(4, updateMap.get("STUDENT_NUM"));
            pstmt.setObject(5, updateMap.get("updateID"));

            // 쿼리 실행
            pstmt.executeUpdate();

            // 입력건수  조회
            updated = pstmt.getUpdateCount();

            // 트랜잭션 COMMIT
            conn.commit();

        } catch (SQLException e) {
            // 오류처리
            System.out.println(e.getMessage());
            // 오류
            updated = -1;
            // 트랜잭션 ROLLBACK
            conn.rollback();
        } finally  {
            // PreparedStatement 종료
            if( pstmt != null ) {
                try {
                    pstmt.close();
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }
        }

        // 결과 반환 - 수정된 데이터 건수
        return updated;
    }

    // 데이터 삭제 함수
    public int deletePerson(int num) throws SQLException {

        //   - 삭제 결과 변수
        int deleted = 0;

        try {
            // PreparedStatement 객체 생성
            pstmt = conn.prepareStatement(DELETE_SQL);

            // 조회 데이터 조건 매핑
            pstmt.setObject(1, num);

            // SQL 실행
            pstmt.executeUpdate();

            // 데이터 삭제 건수
            deleted = pstmt.getUpdateCount();

            // 트랜잭션 COMMIT
            conn.commit();

        } catch (SQLException e) {
            // 오류처리
            System.out.println(e.getMessage());
            // 오류
            deleted = -1;
            // 트랜잭션 ROLLBACK
            conn.commit();
        } finally  {
            // PreparedStatement 종료
            if( pstmt != null ) {
                try {
                    pstmt.close();
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }
        }

        // 결과 반환 - 삭제된 데이터 건수
        return deleted;
    }
}