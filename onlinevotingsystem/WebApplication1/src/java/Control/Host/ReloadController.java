/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control.Host;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class ReloadController implements ServletContextListener {

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Khởi tạo Timer khi ứng dụng web được khởi động
        timer = new Timer();
        timer.schedule(new MyTask(servletContextEvent), 0, 5000); // Chạy sau 0ms và lặp lại sau mỗi 1 giờ (60 phút)
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ReloadController is destroyed.");
        // Hủy bỏ Timer khi ứng dụng web bị dừng
        timer.cancel();
    }

    private class MyTask extends TimerTask {

        private final ServletContextEvent servletContextEvent;

        public MyTask(ServletContextEvent servletContextEvent) {
            this.servletContextEvent = servletContextEvent;
        }

        @Override
        public void run() {
            ServletContext servletContext = servletContextEvent.getServletContext();
            List<Integer> data = (List<Integer>) servletContext.getAttribute("data");

            if (data == null) {
                data = new ArrayList<>();
            }

            // Cập nhật dữ liệu vào danh sách hiện tại
            data.clear();
            data.addAll(getData());

            servletContext.setAttribute("data", data);
        }
    }

    private List<Integer> getData() {
        // Phương thức này dùng để lấy dữ liệu từ nguồn dữ liệu của bạn
        // Trong ví dụ này, chúng ta chỉ trả về một danh sách số nguyên giả định
        List<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(2);
        data.add(3);
        return data;
    }
}
