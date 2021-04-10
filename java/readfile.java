static String[] readFile(String fileName) throws Exception
    {
        List<String> tmp = new ArrayList<>();
        BufferedReader myReader = new BufferedReader(new FileReader(fileName));
        String data;
        while ((data = myReader.readLine()) != null) {    
            if (data.equals(".")) continue;
            tmp.add(data);
        }
        myReader.close();
        String[] ans = new String[tmp.size()];
        for (int i = 0; i < ans.length; i++) ans[i] = tmp.get(i);
        return ans;
    }

