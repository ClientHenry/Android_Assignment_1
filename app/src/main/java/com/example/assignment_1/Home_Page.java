package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Home_Page extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getIntent().getStringExtra("category");

        //     searchViewAction();
        optionRecycler();
        load_recycler();
        bottomNavigation();
        //      loadInfo();

    }

    private void load_recycler() {

        RecyclerView rc = findViewById(R.id.recycler_list);
        List<Product> x;

        if (getIntent().getStringExtra("category") == null) {
            x = MyDataBase.getInstance(getApplicationContext()).productDao().getAllProducts();
        } else {
            String category = getIntent().getStringExtra("category");
            x = MyDataBase.getInstance(getApplicationContext()).productDao().getProductsByCategory(category);
        }

        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(new Deal_List_Adapter(x));
    }

    private void optionRecycler() {
        RecyclerView rc_option = findViewById(R.id.recycler_option);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        rc_option.setLayoutManager(lm);

        ArrayList<Option_Item> optionItems = new ArrayList<>();

        optionItems.add(new Option_Item("All", R.drawable.option_all));
        optionItems.add(new Option_Item("Nature", R.drawable.option_nature));
        optionItems.add(new Option_Item("Gourmet", R.drawable.option_gourmet));
        optionItems.add(new Option_Item("Wonder", R.drawable.option_wonder));
        optionItems.add(new Option_Item("Relic", R.drawable.option_relic));
        rc_option.setAdapter(new OptionAdapter(optionItems));
    }

    private void dealListRecycler() {

        RecyclerView rc = findViewById(R.id.recycler_list);
        rc.setLayoutManager(new LinearLayoutManager(this));
        List<Product> x = MyDataBase.getInstance(getApplicationContext()).productDao().getAllProducts();
        rc.setAdapter(new Deal_List_Adapter(x));
    }

/*
    private void searchViewAction(){

        SearchView searchView = findViewById(R.id.searchView);
        TextView textView = findViewById(R.id.textView);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                textView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }*/

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_2) {
                    Intent intent;
                    Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getLogin();
                    Supplier supplier = MyDataBase.getInstance(getApplicationContext()).supplierDao().getLogin();
                    Admin admin = MyDataBase.getInstance(getApplicationContext()).adminDao().getLogin();
                    if (customer != null) {
                        intent = new Intent(Home_Page.this, Customer_Page.class);
                        intent.putExtra("customer", customer.getName());
                    } else if (supplier != null) {
                        intent = new Intent(Home_Page.this, Supplier_Page.class);
                        intent.putExtra("supplier", supplier.getName());
                    } else if (admin != null) {
                        intent = new Intent(Home_Page.this, Admin_Page.class);
                    } else {
                        intent = new Intent(Home_Page.this, Login_Page.class);
                    }
                    startActivity(intent);
                    return true;
                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }
/*
    public void loadInfo(){

        Admin admin = new Admin("admin", "123456");
        MyDataBase.getInstance(getApplicationContext()).adminDao().insert(admin);

        ArrayList<Customer> customer_lists = new ArrayList<>();
        Customer customer1 = new Customer("Lucy", "Yg9b6D2", "New York", "628-493-1750");
        Customer customer2 = new Customer("David", "4x7L8Wt", "Los Angeles", "841-269-3705");
        Customer customer3 = new Customer("Sharren", "2mP9Bh5", "Chicago", "367-518-4902");
        Customer customer4 = new Customer("Min", "3Qr7Kd6", "Houston", "974-352-6081");
        Customer customer5 = new Customer("Joel", "Jw5S9Xf", "San Francisco", "215-786-4039");
        Customer customer6 = new Customer("Ross", "8M2z4Cj", "Seattle", "459-127-8306");
        Customer customer7 = new Customer("Monica", "6n7aY3G", "Miami", "732-695-4180");
        Customer customer8 = new Customer("Rachel", "5Nh9Ux8", "Las Vegas", "586-841-2307");
        Customer customer9 = new Customer("Phoebe", "Tb2g4L7", "Orlando", "403-671-9285");
        Customer customer10 = new Customer("Chandler", "8Xz6cV9", "San Diego", "168-532-7914");

        customer_lists.add(customer1);
        customer_lists.add(customer2);
        customer_lists.add(customer3);
        customer_lists.add(customer4);
        customer_lists.add(customer5);
        customer_lists.add(customer6);
        customer_lists.add(customer7);
        customer_lists.add(customer8);
        customer_lists.add(customer9);
        customer_lists.add(customer10);

        for(Customer x: customer_lists){
            MyDataBase.getInstance(getApplicationContext()).customerDao().insert(x);
        }


        ArrayList<Supplier> supplier_lists = new ArrayList<>();
        Supplier supplier1 = new Supplier("Henry", "3Ym7Kq4", "Bei Jing", "725-164-3908");
        Supplier supplier2 = new Supplier("Darren", "5Wf2nP6", "Shang Hai", "294-856-1307");
        Supplier supplier3 = new Supplier("Jenny", "9Jx7Rt8", "Guang Zhou", "639-472-8015");
        Supplier supplier4 = new Supplier("Lily", "4Sj3Zr2", "Shen Zhen", "851-307-6429");
        Supplier supplier5 = new Supplier("Tom", "6Ld5Bt9", "Cheng Du", "472-930-1856");
        Supplier supplier6 = new Supplier("Jerry", "7Xp2Kf5", "Tsing Dao", "307-619-4825");
        Supplier supplier7 = new Supplier("Marry", "2Yc8Gw3", "Shang Hai", "963-518-4702");
        Supplier supplier8 = new Supplier("John", "9E4aN6b", "Cheng Du", "105-842-7396");
        Supplier supplier9 = new Supplier("Linda", "7m2Wq5z", "Bei Jing", "714-385-6921");
        Supplier supplier10 = new Supplier("David", "3Pj8Rd4", "Kun Ming", "589-461-7302");

        supplier_lists.add(supplier1);
        supplier_lists.add(supplier2);
        supplier_lists.add(supplier3);
        supplier_lists.add(supplier4);
        supplier_lists.add(supplier5);
        supplier_lists.add(supplier6);
        supplier_lists.add(supplier7);
        supplier_lists.add(supplier8);
        supplier_lists.add(supplier9);
        supplier_lists.add(supplier10);

        for(Supplier x: supplier_lists){
            MyDataBase.getInstance(getApplicationContext()).supplierDao().insert(x);
        }






        ArrayList<Product> deal_lists = new ArrayList<>();
        deal_lists.add(new Product("GuiZhou Bridge", "Wonder", 100, 10, "The Guizhou Bridge, an engineering marvel in China's southwest, " +
                "elegantly spans rivers and valleys, connecting communities with its towering structure and intricate cables. Symbolizing progress and connectivity, it's both a vital " +
                "transportation link and a testament to human ambition. With its striking design, it attracts visitors worldwide, showcasing the beauty of engineering excellence.\n",
                R.drawable.bridge_1, R.drawable.bridge_2, R.drawable.bridge_3, R.drawable.bridge_4, 1, "June 1 - 15", "4.4"));

        deal_lists.add(new Product("Potala Palace", "Relic", 200, 15, "The Potala Palace, perched majestically on Red Hill in Lhasa, Tibet, is an iconic symbol " +
                "of Tibetan Buddhism and cultural heritage. Its grand architecture, with towering white walls and golden roofs, mesmerizes visitors from around the world. Serving as both a spiritual " +
                "sanctuary and a historical treasure, the Potala Palace embodies the rich history and religious significance of Tibet. As a UNESCO World Heritage Site, it stands as a testament to the " +
                "enduring legacy of Tibetan culture and remains a revered pilgrimage destination for Buddhists and tourists alike.\n",
                R.drawable.buda_1, R.drawable.buda_2, R.drawable.buda_3, R.drawable.buda_4, 1, "Feb. 10 - 28", "4.5"));
        deal_lists.add(new Product("Mount Everest", "Nature", 300, 20, "Mount Everest, located in the majestic Himalayas, is home to the world's highest peak, " +
                "Mount Everest. Offering awe-inspiring vistas of snow-capped peaks and breathtaking landscapes, it captivates adventurers and nature enthusiasts alike. As the ultimate destination for " +
                "mountaineers, it presents an unparalleled challenge and a once-in-a-lifetime opportunity to conquer Earth's highest summit. Beyond the thrill of summiting, the region's rich biodiversity " +
                "and unique cultural heritage add depth to the experience, making it a must-visit destination for those seeking adventure, beauty, and spiritual enrichment.\n",
                R.drawable.everest_1, R.drawable.everest_2, R.drawable.everest_3, R.drawable.everest_4, 2, "May 5 - 15", "4.6"));
        deal_lists.add(new Product("FAST", "Wonder", 200, 15, "FAST (Five-hundred-meter Aperture Spherical radio Tele" +
                "scope) is the world's largest single-dish radio telescope, situated in Guizhou, China. With a diameter of 500 meters, it boasts unparalleled " +
                "sensitivity, making it a powerful tool for exploring the universe. FAST's innovative design allows it to scan the skies with remarkable " +
                "precision, capturing radio waves from distant celestial objects. It plays a crucial role in astronomical research, enabling scientists " +
                "to study pulsars, galaxies, and the origins of the universe. As a beacon of scientific advancement, FAST represents humanity's quest to " +
                "unravel the mysteries of the cosmos.\n", R.drawable.fast_1, R.drawable.fast_2, R.drawable.fast_3, R.drawable.fast_4, 2, "July 1 - 15", "4.7"));
        deal_lists.add(new Product("Great Wall", "Relic", 300, 20, "The Great Wall of China, a UNESCO World Heritage Site, is an " +
                "architectural marvel that spans thousands of miles across northern China. Built over centuries to protect against invasions, it " +
                "stands as a testament to human ingenuity and perseverance. The wall's imposing fortifications and stunning landscapes attract " +
                "millions of visitors each year, offering a glimpse into China's rich history and cultural heritage. As a symbol of strength and " +
                "unity, the Great Wall remains an enduring icon of Chinese civilization, inspiring awe and admiration around the world.\n",
                R.drawable.wall_1, R.drawable.wall_2, R.drawable.wall_3, R.drawable.wall_4, 3, "Aug. 10 - 20", "4.8"));
        deal_lists.add(new Product("Panda Center", "Nature", 100, 10, "The Giant Panda Research and Conservation Center, located in China, " +
                "is dedicated to the protection and preservation of the beloved giant panda species. As one of the world's leading institutions for panda research " +
                "and breeding, the center plays a vital role in conservation efforts aimed at safeguarding these endangered animals. Through innovative research, " +
                "habitat preservation, and captive breeding programs, the center works to increase the panda population and ensure their survival in the wild. " +
                "Visitors have the opportunity to observe pandas in their natural habitat, learn about conservation efforts, and contribute to the ongoing mission" +
                " of protecting these iconic creatures for future generations.\n", R.drawable.panda_1, R.drawable.panda_2, R.drawable.panda_3, R.drawable.panda_4, 3
                , "Sept. 1 - 15", "4.9"));
        deal_lists.add(new Product("Terracotta Army", "Relic", 200, 15, "The Terracotta Army, also known as the Terracotta Warriors and Horses, " +
                "is a collection of life-sized clay sculptures unearthed in the Shaanxi province of China. Dating back over 2,000 years, these remarkable " +
                "sculptures were created to guard the tomb of Qin Shi Huang, the first Emperor of China. The Terracotta Army comprises thousands of intricately " +
                "crafted soldiers, horses, and chariots, each with unique facial features and expressions. This archaeological marvel " +
                "offers insight into ancient Chinese military practices, craftsmanship, and beliefs about the afterlife. Today, the Terracotta Army is a UNESCO " +
                "World Heritage Site and a testament to the ingenuity and cultural significance of ancient China.\n", R.drawable.warrior_1, R.drawable.warrior_2,
                R.drawable.warrior_3, R.drawable.warrior_4, 4, "Oct. 20 - 31", "4.8"));
        deal_lists.add(new Product("Yarlung Tsangpo River", "Nature", 300, 20, "The Yarlung Tsangpo River, also known as the Brahmaputra " +
                "River in India, is one of the longest and most significant rivers in Asia. Originating from the Angsi Glacier in the Himalayas, it flows through Tibet, China, and India, " +
                "before eventually emptying into the Bay of Bengal. Known for its rugged terrain, deep gorges, and powerful rapids, the Yarlung Tsangpo River offers breathtaking scenery and " +
                "abundant biodiversity along its course. It plays a vital role in the livelihoods of millions of people, supporting agriculture, transportation, and hydroelectric power generation. " +
                "The Yarlung Tsangpo River holds cultural and spiritual significance for the Tibetan people and is revered as one of the world's great rivers.\n", R.drawable.yalu_1, R.drawable.yalu_2,
                R.drawable.yalu_3, R.drawable.yalu_4, 4, "Nov. 5 - 25", "4.9"));
        deal_lists.add(new Product("Miao Villiage", "Relic", 100, 10, "Miao Village, nestled in the scenic mountains of China, offers a glimpse into the rich culture " +
                "and traditions of the Miao ethnic minority. With its distinctive wooden houses, intricate handicrafts, and vibrant festivals, Miao Village is a treasure trove of cultural heritage. Visitors " +
                "can explore the winding cobblestone streets, witness traditional ceremonies, and admire the colorful traditional costumes worn by the locals. The village's picturesque setting, surrounded by " +
                "lush greenery and terraced fields, adds to its allure. Miao Village provides an immersive experience into the customs and way of life of the Miao people, making it a must-visit destination for " +
                "cultural enthusiasts and travelers seeking authenticity.\n", R.drawable.village_1, R.drawable.village_2, R.drawable.village_3, R.drawable.village_4, 5, "Dec. 1 - 15", "4.7"));
        deal_lists.add(new Product("Sichua Cuisine", "Gourmet", 80, 10, "Sichuan cuisine, also known as Szechuan cuisine, hails from the Sichuan province in southwest China. " +
                "Renowned for its bold flavors, fiery spices, and numbing sensation from Sichuan peppercorns, it is one of the most celebrated culinary traditions in China. Sichuan cuisine features a diverse range " +
                "of dishes, including mapo tofu, kung pao chicken, and hotpot, all known for their distinctive aroma and complex flavor profiles. With its emphasis on chili peppers, garlic, ginger, and Sichuan " +
                "peppercorns, Sichuan cuisine delights the palate with its combination of heat, sweetness, and umami. It's a favorite among spice enthusiasts and food lovers worldwide.",
                R.drawable.sichuandish_1, R.drawable.sichuandish_2, R.drawable.sichuandish_3, R.drawable.sichuandish_4, 5, "Jan. 10 - 20", "4.6"));
        deal_lists.add(new Product("Lu Cuisine", "Gourmet", 100, 10, "Lu Cuisine, originating from Shandong Province in China, is one of the eight major culinary traditions in Chinese cuisine. " +
                "Known for its emphasis on fresh ingredients and simple cooking techniques, Lu Cuisine showcases the natural flavors and textures of its dishes. Characterized by its use of seafood, poultry, " +
                "and vegetables, it offers a diverse range of flavors, from savory to sweet and sour. Popular dishes include braised sea cucumber, sweet and sour carp, and Shandong-style dumplings. Renowned " +
                "for its hearty and satisfying meals, Lu Cuisine reflects the culinary traditions and cultural heritage of the Shandong region.\n ",
                R.drawable.shandong_1, R.drawable.shandong_2, R.drawable.shandong_3, R.drawable.shandong_4, 6, "Feb. 1 - 29", "4.5"));
        deal_lists.add(new Product("Sanxingdui", "Relic", 120, 10, "Sanxingdui, located in Sichuan Province, China, is an archaeological site renowned for its mysterious ancient artifacts and " +
                "ruins dating back over 3,000 years. Discovered in the 20th century, Sanxingdui has yielded an array of remarkable relics, including bronze masks, sculptures, and ritualistic " +
                "objects, shedding light on an enigmatic civilization that once thrived in the region. Believed to be the remnants of the Shu Kingdom, Sanxingdui offers insights into an " +
                "advanced and sophisticated culture that existed during the Bronze Age. The site has captivated archaeologists and historians, sparking intrigue and fascination with its " +
                "tantalizing glimpses into ancient Chinese history and civilization.\n", R.drawable.sanxing_1, R.drawable.sanxing_2, R.drawable.sanxing_3, R.drawable.sanxing_4, 6, "Mar. 1 - 15", "4.4"));
        deal_lists.add(new Product("Lijiang", "Nature", 150, 10, "Lijiang, a UNESCO World Heritage Site located in Yunnan Province, China, is renowned for " +
                "its ancient town, picturesque scenery, and vibrant culture. Nestled among the snow-capped peaks of the Himalayas, Lijiang's Old Town is a maze of cobblestone streets, traditional " +
                "wooden houses, and flowing canals, offering a glimpse into China's rich history and ethnic diversity. The town is home to the Naxi ethnic minority, known for their unique " +
                "Dongba culture and traditional music. Visitors can explore ancient temples, enjoy panoramic views from Jade Dragon Snow Mountain, and experience the warm hospitality of the " +
                "local people. Lijiang is a timeless destination that captivates travelers with its charm and allure.\n", R.drawable.lijiang_1, R.drawable.lijiang_2, R.drawable.lijiang_3, R.drawable.lijiang_4, 7
                , "Apr. 1 - 15", "4.3"));
        deal_lists.add(new Product("Honghe Terraces", "Wonder", 200, 15, "The Honghe Hani Rice Terraces, located in Yunnan Province, China, are a UNESCO World Heritage Site " +
                "celebrated for their stunning beauty and intricate agricultural engineering. Carved into the mountainsides by the Hani people over generations, these terraces form a " +
                "breathtaking landscape of cascading rice paddies that stretch as far as the eye can see. Renowned for their sustainable farming practices and harmonious integration with " +
                "the natural environment, the terraces showcase the ingenuity and cultural heritage of the Hani ethnic group. Visitors can explore the terraces, " +
                "witness traditional farming methods, and immerse themselves in the rich cultural traditions of this ancient agricultural wonder.\n",
                R.drawable.landscape_1, R.drawable.landscape_2, R.drawable.landscape_3, R.drawable.landscape_4, 7, "May 20 - 30", "4.2"));
        deal_lists.add(new Product("Qiandao Lake", "Nature", 250, 20, "Qiandao Lake, also known as Thousand Island Lake, is a stunning man-made reservoir situated in " +
                "Zhejiang Province, China. Renowned for its crystal-clear waters and scenic beauty, it is surrounded by lush green mountains and dotted with numerous small islands. The lake's name" +
                " derives from the countless islands scattered across its surface, creating a picturesque landscape that attracts visitors from around the world. Qiandao Lake offers a wide " +
                "range of recreational activities, including boating, fishing, hiking, and sightseeing. Its tranquil atmosphere and natural splendor make it a " +
                "popular destination for relaxation and outdoor adventure.\n", R.drawable.lake_1, R.drawable.lake_2, R.drawable.lake_3, R.drawable.lake_4, 8, "June 1 - 15", "4.1"));
        deal_lists.add(new Product("Kekexili", "Nature", 300, 20, "Hailed as one of China's most pristine and untouched wilderness areas, Kekexili Nature " +
                "Reserve, also known as Hoh Xil, is located on the Qinghai-Tibet Plateau. Spanning vast expanses of rugged terrain, Kekexili is renowned for its breathtaking landscapes, including " +
                "snow-capped mountains, expansive grasslands, and crystal-clear lakes. This remote and isolated region serves as a crucial habitat for endangered species such as the Tibetan " +
                "antelope and wild yak. Protected as a UNESCO World Heritage Site, Kekexili attracts adventurers and nature enthusiasts seeking to explore its untamed beauty and observe " +
                "its unique wildlife in their natural habitat.\n", R.drawable.keke_1, R.drawable.keke_2, R.drawable.keke_3, R.drawable.keke_4, 8, "Oct. 5 - 15", "4.7"));
        deal_lists.add(new Product("Huaiyang Cuisine", "Gourmet", 100, 10, "Huaiyang cuisine, originating from the Jiangsu province in eastern China, is one of the " +
                "four major culinary traditions in Chinese cuisine. Known for its delicate flavors, meticulous preparation, and artistic presentation, Huaiyang cuisine is celebrated for its " +
                "sophistication and elegance. Featuring fresh seafood, tender meats, and seasonal vegetables, it offers a diverse range of dishes, from braised pork belly to sweet and sour " +
                "fish. With its emphasis on light, natural flavors and harmonious combinations, Huaiyang cuisine delights the palate with its balance of textures and tastes. It is a favorite " +
                "among food connoisseurs and gourmands seeking refined dining experiences.\n", R.drawable.huaiyang_1, R.drawable.huaiyang_2, R.drawable.huaiyang_3, R.drawable.huaiyang_4, 9
                , "Nov. 1 - 15", "4.6"));
        deal_lists.add(new Product("HZMB", "Wonder", 100, 15, "The Hong Kong-Zhuhai-Macao Bridge (HZMB) is a remarkable infrastructure project linking the " +
                "cities of Hong Kong, Zhuhai, and Macao in the Pearl River Delta region of China. Spanning over 55 kilometers, it is the world's longest sea-crossing bridge. " +
                "This architectural marvel includes a series of bridges and tunnels, providing a vital transportation link between the three cities. The HZMB facilitates economic " +
                "integration, tourism, and cultural exchange, serving as a symbol of connectivity and progress in the Greater Bay Area. With its impressive engineering and " +
                "strategic significance, the HZMB has become a landmark achievement in modern infrastructure development.\n", R.drawable.hongkong_1, R.drawable.hongkong_2, R.drawable.hongkong_3, R.drawable.hongkong_4, 9
                , "Dec. 20 - 31", "4.5"));
        deal_lists.add(new Product("Harbin Ice and Snow World", "Wonder", 200, 20, "Harbin Ice and Snow World is an enchanting winter wonderland located in Harbin, China. " +
                "It is renowned for its spectacular ice sculptures and elaborate snow creations, making it a must-visit destination during the winter season. Visitors can marvel at intricately " +
                "carved ice structures, illuminated by colorful lights, creating a magical atmosphere. The park offers various attractions, including ice slides, snow sculptures, " +
                "and ice buildings, providing endless opportunities for fun and exploration. As one of the largest ice and snow festivals in the world, Harbin Ice and Snow World showcases " +
                "the beauty and creativity of winter artistry, attracting tourists from across the globe to experience its breathtaking beauty firsthand.\n",
                R.drawable.harbin_1, R.drawable.harbin_2, R.drawable.harbin_3, R.drawable.harbin_4, 10, "April 5 - 15", "4.4"));
        deal_lists.add(new Product("Cantonese Cuisine", "Gourmet", 300, 20, "Cantonese cuisine, originating from Guangdong province in China, is one of the four major culinary " +
                "traditions in Chinese cuisine, cherished by many. Its unique cooking techniques and diverse range of ingredients reflect its distinct charm. Characterized by its light, " +
                "fresh, and aromatic flavors, Cantonese cuisine emphasizes the preservation of the original tastes and textures of ingredients while achieving a harmonious balance of " +
                "colors and aromas. Cooking methods vary, including roasting, braising, steaming, and frying, with renowned dishes such as Cantonese barbecue, poached chicken, steamed " +
                "fish, and sweet and sour pork. As an integral part of Chinese culinary culture, Cantonese cuisine is celebrated worldwide for its meticulous craftsmanship and exceptional " +
                "flavors, attracting praise and admiration from food enthusiasts globally.\n", R.drawable.guangdong_1, R.drawable.guangdong_2, R.drawable.guangdong_3, R.drawable.guangdong_4, 10
                , "Nov. 10 - 20", "4.3"));

        for(Product x: deal_lists){
            MyDataBase.getInstance(getApplicationContext()).productDao().insert(x);
        }


        ArrayList<Deal> my_deal_lists = new ArrayList<>();
        Deal deal1 = new Deal(1, 1);
        Deal deal2 = new Deal(2, 1);
        Deal deal3 = new Deal(3, 2);
        Deal deal4 = new Deal(4, 2);
        Deal deal5 = new Deal(5, 3);
        Deal deal6 = new Deal(6, 3);
        Deal deal7 = new Deal(7, 4);
        Deal deal8 = new Deal(8, 4);
        Deal deal9 = new Deal(9, 5);
        Deal deal10 = new Deal(10, 5);
        Deal deal11 = new Deal(11, 6);
        Deal deal12 = new Deal(12, 7);
        Deal deal13 = new Deal(13, 8);
        Deal deal14 = new Deal(14, 9);
        Deal deal15 = new Deal(15, 10);


        my_deal_lists.add(deal1);
        my_deal_lists.add(deal2);
        my_deal_lists.add(deal3);
        my_deal_lists.add(deal4);
        my_deal_lists.add(deal5);
        my_deal_lists.add(deal6);
        my_deal_lists.add(deal7);
        my_deal_lists.add(deal8);
        my_deal_lists.add(deal9);
        my_deal_lists.add(deal10);
        my_deal_lists.add(deal11);
        my_deal_lists.add(deal12);
        my_deal_lists.add(deal13);
        my_deal_lists.add(deal14);
        my_deal_lists.add(deal15);

        for(Deal x: my_deal_lists){
            MyDataBase.getInstance(getApplicationContext()).dealDao().insert(x);
        }

    }*/

}