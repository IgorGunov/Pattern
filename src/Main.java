interface X5Group {
    void accept(Visitor visitor);
}

class Perekrestok implements X5Group {
    private String name = "perekrestok";

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPerekrestok(this);
    }
}

class Pyaterochka implements X5Group {
    private String name = "pyaterocha";

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPyterochka(this);
    }
}

interface Visitor {
    void visitPerekrestok(Perekrestok element);
    void visitPyterochka(Pyaterochka element);
}

class Buyer implements Visitor {
    @Override
    public void visitPerekrestok(Perekrestok element) {
        System.out.println("Покупатель посещает магазин "+  element.getName() + " и начинает делать покупки");
    }

    @Override
    public void visitPyterochka(Pyaterochka element) {
        System.out.println("Покупатель посещает магазин "+  element.getName() + " и начинает делать покупки");
    }
}

class Inspektor implements Visitor {
    @Override
    public void visitPerekrestok(Perekrestok element) {
        System.out.println("Инспектор посещает магазин "+  element.getName() + " и начинает делать проверку");
    }

    @Override
    public void visitPyterochka(Pyaterochka element) {
        System.out.println("Инспектор посещает магазин "+  element.getName() + " и начинает делать проверку");
    }
}

abstract class DecoratorVisitor implements Visitor {
    private Visitor visitor;

    public DecoratorVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void visitPerekrestok(Perekrestok element) {
        visitor.visitPerekrestok(element);
    }

    @Override
    public void visitPyterochka(Pyaterochka element) {
        visitor.visitPyterochka(element);
    }
}

class DecoratorCardExt extends DecoratorVisitor {

    public DecoratorCardExt(Visitor visitor) {
        super(visitor);
    }

    @Override
    public void visitPerekrestok(Perekrestok element) {
        System.out.println("Посетитель имеет скидочную карту магазина " + element.getName());
        super.visitPerekrestok(element);
        System.out.println("Посетитель применяет скидочную карту скидочную карту в " + element.getName());
    }

    @Override
    public void visitPyterochka(Pyaterochka element) {
        System.out.println("Посетитель имеет скидочную карту магазина " + element.getName());
        super.visitPyterochka(element);
        System.out.println("Посетитель применяет скидочную карту скидочную карту в " + element.getName());
    }
}



public class Main {
    public static void main(String[] args) {
        X5Group[] elements = {new Perekrestok(), new Pyaterochka()};
        Visitor visitor = new Inspektor();
        Visitor visitor2 = new Buyer();
        Visitor decoratorVisitor = new DecoratorCardExt(visitor);
        Visitor decoratorVisitor2 = new DecoratorCardExt(visitor2);

        for (X5Group element : elements) {
            element.accept(decoratorVisitor);
        }
        System.out.println("====================================");
        for (X5Group element : elements) {
            element.accept(decoratorVisitor2);
        }
    }
}