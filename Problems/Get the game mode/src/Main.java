class Problem {
    public static void main(String[] args) {
        String[] params = args;
        int index = -1;
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("mode")) {
                index = i;
            }
        }

        if (index != -1) {
            System.out.println(params[index + 1]);
        } else {
            System.out.println("default");
        }
    }
}