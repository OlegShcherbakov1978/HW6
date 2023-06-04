import java.util.*;

public class Ноутбук {
    private String модель;
    private int ОЗУ;
    private int объемЖД;
    private String операционнаяСистема;
    private String цвет;

    public Ноутбук(String модель, int ОЗУ, int объемЖД, String операционнаяСистема, String цвет) {
        this.модель = модель;
        this.ОЗУ = ОЗУ;
        this.объемЖД = объемЖД;
        this.операционнаяСистема = операционнаяСистема;
        this.цвет = цвет;
    }

    // Геттеры и сеттеры для полей

    public String getМодель() {
        return модель;
    }

    public void setМодель(String модель) {
        this.модель = модель;
    }

    public int getОЗУ() {
        return ОЗУ;
    }

    public void setОЗУ(int ОЗУ) {
        this.ОЗУ = ОЗУ;
    }

    public int getОбъемЖД() {
        return объемЖД;
    }

    public void setОбъемЖД(int объемЖД) {
        this.объемЖД = объемЖД;
    }

    public String getОперационнаяСистема() {
        return операционнаяСистема;
    }

    public void setОперационнаяСистема(String операционнаяСистема) {
        this.операционнаяСистема = операционнаяСистема;
    }

    public String getЦвет() {
        return цвет;
    }

    public void setЦвет(String цвет) {
        this.цвет = цвет;
    }

    @Override
    public String toString() {
        return "Ноутбук{" +
                "модель='" + модель + '\'' +
                ", ОЗУ=" + ОЗУ +
                ", объемЖД=" + объемЖД +
                ", операционнаяСистема='" + операционнаяСистема + '\'' +
                ", цвет='" + цвет + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Set<Ноутбук> ноутбуки = createНоутбуки();

        Map<Integer, String> критерии = new HashMap<>();
        критерии.put(1, "ОЗУ");
        критерии.put(2, "ОбъемЖД");
        критерии.put(3, "ОперационнаяСистема");
        критерии.put(4, "Цвет");

        Map<String, Object> значенияКритериев = getЗначенияКритериев(критерии);

        Set<Ноутбук> отфильтрованныеНоутбуки = фильтроватьНоутбуки(ноутбуки, критерии, значенияКритериев);

        for (Ноутбук ноутбук : отфильтрованныеНоутбуки) {
            System.out.println(ноутбук);
        }
    }

    public static Set<Ноутбук> createНоутбуки() {
        Set<Ноутбук> ноутбуки = new HashSet<>();
        ноутбуки.add(new Ноутбук("Dell", 8, 512, "Windows 10", "Серебристый"));
        ноутбуки.add(new Ноутбук("HP", 16, 1024, "Windows 10", "Черный"));
        ноутбуки.add(new Ноутбук("Lenovo", 8, 256, "Windows 10", "Синий"));
        ноутбуки.add(new Ноутбук("Apple", 16, 512, "macOS", "Серый"));
        ноутбуки.add(new Ноутбук("Asus", 8, 512, "Windows 10", "Черный"));
        return ноутбуки;
    }

    public static Map<String, Object> getЗначенияКритериев(Map<Integer, String> критерии) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> значенияКритериев = new HashMap<>();

        for (Map.Entry<Integer, String> entry : критерии.entrySet()) {
            int номерКритерия = entry.getKey();
            String критерий = entry.getValue();

            System.out.print("Введите минимальное значение для критерия '" + критерий + "': ");
            Object значение = null;

            switch (номерКритерия) {
                case 1:
                case 2:
                    значение = scanner.nextInt();
                    break;
                case 3:
                case 4:
                    scanner.nextLine(); // Пропустить остаток строки после ввода числа
                    значение = scanner.nextLine();
                    break;
            }

            значенияКритериев.put(критерий, значение);
        }

        return значенияКритериев;
    }

    public static Set<Ноутбук> фильтроватьНоутбуки(Set<Ноутбук> ноутбуки, Map<Integer, String> критерии, Map<String, Object> значенияКритериев) {
        Set<Ноутбук> отфильтрованныеНоутбуки = new HashSet<>();

        for (Ноутбук ноутбук : ноутбуки) {
            boolean удовлетворяетФильтру = true;

            for (Map.Entry<Integer, String> entry : критерии.entrySet()) {
                int номерКритерия = entry.getKey();
                String критерий = entry.getValue();
                Object значение = значенияКритериев.get(критерий);

                switch (номерКритерия) {
                    case 1:
                        if (ноутбук.getОЗУ() < (int) значение) {
                            удовлетворяетФильтру = false;
                        }
                        break;
                    case 2:
                        if (ноутбук.getОбъемЖД() < (int) значение) {
                            удовлетворяетФильтру = false;
                        }
                        break;
                    case 3:
                        if (!ноутбук.getОперационнаяСистема().equalsIgnoreCase((String) значение)) {
                            удовлетворяетФильтру = false;
                        }
                        break;
                    case 4:
                        if (!ноутбук.getЦвет().equalsIgnoreCase((String) значение)) {
                            удовлетворяетФильтру = false;
                        }
                        break;
                }

                if (!удовлетворяетФильтру) {
                    break;
                }
            }

            if (удовлетворяетФильтру) {
                отфильтрованныеНоутбуки.add(ноутбук);
            }
        }

        return отфильтрованныеНоутбуки;
    }
}
