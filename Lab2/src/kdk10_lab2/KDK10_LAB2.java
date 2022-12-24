package kdk10_lab2;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class KDK10_LAB2 {

    public static void main(String[] args) {
        try {
            // ����� ����� ���� ������ "tsn_demo" �� ��������� ���������� (localhost)
            String url = "jdbc:mysql://" + InetAddress.getLocalHost().getHostName() + ".localhost"
                    + ":3306/task2?serverTimezone=UTC&useSSL=false";

            // �������� ������� ���������� � ����� ������
            Properties authorization = new Properties();
            authorization.put("user", "root"); // ������� ��� ������������ ��
            authorization.put("password", "D9252445ad"); // ������� ������ ������� � ��

            // �������� ���������� � ����� ������
            Connection connection = DriverManager.getConnection(url, authorization);

            // �������� ��������� ������� � ���� ������
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // ���������� ������� � ���� ������, ��������� ������ ������
            ResultSet table = statement.executeQuery("SELECT * FROM human_res");

            System.out.println("��������� ������� ��:");
            table.first(); // ������� ����� �����
            for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                System.out.print(table.getMetaData().getColumnName(j) + "\t\t");
            }
            System.out.println();
            int o = 0;
            table.beforeFirst(); // ������� ������ �������
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                    o=table.getRow()+1;
                }
                System.out.println();
            }
            System.out.println();

            Scanner sc = new Scanner(System.in);
            System.out.println("������� ��������� ����� ������ ��� ������� ������:");
            System.out.print("���: ");
            String scannedName = sc.nextLine();
            System.out.print("�������: ");
            String scannedSurname = sc.nextLine();
            System.out.print("���������: ");
            String scannedJobTitle = sc.nextLine();
            System.out.print("��� ��������: ");
            String scannedDate = sc.nextLine();
            System.out.println();

            System.out.println("����� ���������� ������:");
            statement.execute("INSERT INTO human_res(id, name, surname, jobtitle, dateofbirth) VALUES ('"+o+"','" + scannedName + "', '" + scannedSurname + "','" + scannedJobTitle + "', '" + scannedDate + "')");
            table = statement.executeQuery("SELECT * FROM human_res");

            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            System.out.println();

            System.out.println("������ � ����� id ������ �������?");
            System.out.print("id: ");
            String scannedId = sc.nextLine();
            if (!scannedId.equals("")) {
                statement.execute("DELETE FROM human_res WHERE id = " + scannedId);
            }
            System.out.println();

            System.out.println("������� ����� �������� ������:");
            table = statement.executeQuery("SELECT * FROM human_res");
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            System.out.println();

            System.out.println("����� ������ �� ������ ��������?");
            System.out.print("id: ");
            scannedId = sc.nextLine();
            System.out.println("������ ������� ����� ������ ��� ������ ������");
            System.out.print("���: ");
            String scannedDateUp = sc.nextLine();
            System.out.print("���������: ");
            String scannedJobTitleUp = sc.nextLine();
            if (!scannedId.equals("")) {
                statement.executeUpdate("UPDATE human_res SET dateofbirth = '" + scannedDateUp + "' WHERE id = " + scannedId);
                statement.executeUpdate("UPDATE human_res SET jobtitle = '" + scannedJobTitleUp + "' WHERE id = " + scannedId);
            }
            System.out.println("������ ������� ����� ���������:");
            table = statement.executeQuery("SELECT * FROM human_res");
            System.out.println();

            System.out.print("������� �������� �������� ��� ����������: ");
            String filter = sc.nextLine();

            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }
            System.out.println();

            System.out.println("������ ������� � �������� � �����������:");
            table = statement.executeQuery("SELECT * FROM human_res WHERE name like '%"
                    + filter + "%' ORDER BY name DESC");

            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }

            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }

            if (table != null) {
                table.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            } // ���������� �� ���� ������

        } catch (Exception e) {
            System.err.println("������ ������� � ���� ��� �� ������� �� ��, ��� ���� !!!");
        }
    }

}
