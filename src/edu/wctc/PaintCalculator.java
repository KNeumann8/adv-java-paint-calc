package edu.wctc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PaintCalculator {

    private ArrayList<Paintable> paintableArrayList = new ArrayList();
    private Scanner keyboard;

    public static void main(String[] args) {
        new PaintCalculator();
    }

    public PaintCalculator() {
        keyboard = new Scanner(System.in);

        int option = 0;

        while (option != 5) {
            printMenu();

            String s = keyboard.nextLine();
            try {
                int choice = Integer.parseInt(s);
                switch (choice) {
                    case 1:
                        createRoom();
                        break;
                    case 2:
                        RoomWriter rw = new RoomWriter();
                        try{
                            rw.writeRoomFile("rooms.dat", paintableArrayList);
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                        break;
                    case 3:
                        RoomReader rr = new RoomReader();
                        try{
                            paintableArrayList = rr.readRoomFile("rooms.dat");
                        }catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        printRooms();
                        break;
                    case 5:
                        createCanvas();

                        break;
                    case 6:
                        System.out.println("Goodbye");
                        System.exit(0);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }
        }

    }

    private void printRooms() {
        if (paintableArrayList.isEmpty()) {
            System.out.println("No rooms yet");
        }

        for (Paintable room : paintableArrayList) {
            System.out.println(room.toString());
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Add room");
        System.out.println("2. Write paintable items to file");
        System.out.println("3. Read paintable items from file");
        System.out.println("4. View paintable items");
        System.out.println("5. Add Canvas");
        System.out.println("6. Exit");
        System.out.println();
    }

    private int promptForDimension(String name) {
        System.out.print("Enter the room's " + name + ": ");
        String response = keyboard.nextLine();
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void createRoom() {
        int length = promptForDimension("length");
        int width = promptForDimension("width");
        int height = promptForDimension("height");

        try {
            Room room = new Room(length, width, height);
            paintableArrayList.add(room);

            System.out.println("Room successfully created");
        } catch (BadWidthException | BadHeightException e) {
            System.out.println("Could not create room.");
        }

    }

    private void createCanvas(){
        boolean loopControl;
        double height = 0, width = 0;
        do{
            loopControl = false;
            try{
                System.out.println("What is the canvas height in feet?");
                height = Double.parseDouble(keyboard.nextLine());
            }catch(NumberFormatException e){
                e.printStackTrace();
                loopControl = true;
            }
        }while(loopControl);

        do{
            loopControl = false;
            try{
                System.out.println("What is the width of the canvas in feet?");
                width = Double.parseDouble(keyboard.nextLine());
            }catch(NumberFormatException e){
                e.printStackTrace();
                loopControl = true;
            }
        }while(loopControl);

        boolean canAdd = true;
        try{
            Canvas newCanvas = new Canvas(width, height);
            if(canAdd){
                paintableArrayList.add(newCanvas);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Creation Failed");
            canAdd = false;
        }

    }
}
