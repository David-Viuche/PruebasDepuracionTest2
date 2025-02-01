package model;

public enum Cargo {
    CAJERO, GERENTE, SUPERVISOR;

    public static boolean isValidCargo(String cargo) {
        for (Cargo c : Cargo.values()) {
            if (c.name().equalsIgnoreCase(cargo)) {
                return true;
            }
        }
        return false;
    }
}
