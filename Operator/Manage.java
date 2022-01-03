package Operator;

import java.io.IOException;

public interface Manage {
    void help();
    void search();
    void modify() throws IOException;
    void insert() throws IOException;
    void delete() throws IOException;
    void display();
}
