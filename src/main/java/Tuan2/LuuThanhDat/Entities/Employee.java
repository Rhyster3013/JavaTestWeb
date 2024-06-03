package Tuan2.LuuThanhDat.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String title;
    private String phone;
    private Date birth;


    // Phương thức định dạng ngày tháng
    public String getFormattedBirthDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(birth);
    }
}
