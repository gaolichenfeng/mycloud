package cn.tedu.sp02.item.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
	//Lombok 的 @Slf4j 会添加下面这行代码:
	//static Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Override
	public List<Item> getItems(String orderId) {
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(new Item(1, "商品1", 1));
		list.add(new Item(2, "商品2", 5));
		list.add(new Item(3, "商品3", 2));
		list.add(new Item(4, "商品4", 7));
		list.add(new Item(5, "商品5", 1));
		return list;
	}

	@Override
	public void decreaseNumbers(List<Item> items) {
		for (Item item : items) {
			log.info("减少商品库存: "+item);
		}
	}

}
