package com.hari.spy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.NotAMockException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
//https://www.baeldung.com/mockito-spy
//list class obj (normal obj creation) -> spy obj(Mockito.spy) -> call method (Mockito.verify)
//verify method(normal method call) -> assert test
public class SpyTest {
	
	@Test
	public void testSpy1() {
		List<String> list = new ArrayList<>();
		List<String> listspy = Mockito.spy(list);
		
		listspy.add("hari");//actual add is called - we are not stubbing method of list
		listspy.add("om");

		Mockito.verify(listspy).add("hari");//verify that method is called or not
		Mockito.verify(listspy).add("om");
		Mockito.verify(listspy, Mockito.never()).add("yadav");
		
		assertEquals(2, listspy.size());
	}
	
	@Test
	//https://www.youtube.com/watch?v=Qq0uziWeMAA&list=PLBXh307tT1zWvWUDMFdvEC1ia972E8kGS&index=2&t=0s
	public void testSpy1_ArgumentCaptor() {
		List<String> list = new ArrayList<>();
		List<String> listspy = Mockito.spy(list);
		
		listspy.add("hari");//actual add is called - we are not stubbing method of list
		
		ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);//this arg will store single string value and later we can use to in assert to test
		
		Mockito.verify(listspy).add(arg.capture());//store that add string argument into argumetcaptor
		
		assertEquals("hari", arg.getValue());//compare argument captor value 
	}
	
	@Test
	public void spyTest2() {
		List<String> list = new ArrayList<String>();
		List<String> spy = Mockito.spy(list);
		
		assertEquals(0, spy.size());
	}
	
	@Test
	public void spyTest3() {
		List<String> list = new ArrayList<String>();
		List<String> spy = Mockito.spy(list);
		
		assertEquals(0, spy.size());
		//override list method - i.e. stubbing list method by using doReturn()
		
		Mockito.doReturn(100).when(spy).size();//when size() method called - return 100 
		Mockito.verify(spy).size();//Optional : verify size is called or not
		
		assertEquals(100, spy.size());//check our stub size output
	}
	
	@Test
	public void spyTest3_1() {
		//List<String> list = new ArrayList<String>();
		List<String> spy = Mockito.spy(ArrayList.class);//pass class name to create spy class
		
		assertEquals(0, spy.size());
		//override list method - i.e. stubbing list method by using doReturn()
		
		Mockito.doReturn(100).when(spy).size();//when size() method called - return 100 
		Mockito.verify(spy).size();//Optional : verify size is called or not
		assertEquals(100, spy.size());//check our stub size output
	}
	
	@Test
	public void spyTest3_1_times__shouldReturn100_whenStubSizeMethodIsCalled() {
		//List<String> list = new ArrayList<String>();
		List<String> spy = Mockito.spy(ArrayList.class);//pass class name to create spy class
		
		assertEquals(0, spy.size());
		//override list method - i.e. stubbing list method by using doReturn()
		
		Mockito.doReturn(100).when(spy).size();//when size() method called - return 100 
		
		Mockito.verify(spy).size();//Optional : verify size is called or not
		Mockito.verify(spy, Mockito.times(1)).size();//Optional : verify with other parameter "times"
		
		assertEquals(100, spy.size());//check our stub size output
	}
	
	@Test
	public void spyTest3_1_times__shouldReturn100_whenStubSizeMethodIsCalled_() {
		List<String> list = new ArrayList<String>();
		List<String> spy = Mockito.spy(list);
		
		Mockito.verify(spy, Mockito.never()).size();//or below times = 0 is same
		Mockito.verify(spy, Mockito.times(0)).size();
		assertEquals(0, spy.size());//before calling size checking its called times
		Mockito.verify(spy, Mockito.times(1)).size();
	}
	
	
	@Test(expected = NotAMockException.class)//adding exception
	public void mockTest1() {
		List<String> list = new ArrayList<String>();
		
		Mockito.doReturn(100).when(list).size();//doReturn takes only mock obj not normal object
		
		assertEquals(100, list.size());//this will throw exception
	}
	
	@Test
	public void mockTest1_() {
		List<String> list = new ArrayList<String>();
		List<String> spy = Mockito.spy(list);
		
		Mockito.doReturn(100).when(spy).size();//doReturn takes only mock obj not normal object
		
		assertEquals(100, spy.size());
	}
	
	@Test
	public void mockListInterface() {//if we run single this method then test pass
		//mock creation
		List list = Mockito.mock(List.class);//interface
		
		//using mock object
		list.add("hari");
		list.clear();
		
		//verification
		Mockito.verify(list).add("hari");
		Mockito.verify(list).clear();
	}
	
	@Test
	public void mockLinkedListClass_() {
		//mock creation
		LinkedList list = Mockito.mock(LinkedList.class);//class
		
		//stubbing
		Mockito.when(list.get(0)).thenReturn("hari");//*easy to understand
		Mockito.doReturn("om").when(list).get(1);
		Mockito.when(list.get(2)).thenReturn(new RuntimeException());
		
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(999));//null
		System.out.println(list.get(2));
		
		Mockito.verify(list).get(0);
		Mockito.verify(list).get(1);
		Mockito.verify(list).get(2);
		//Mockito.verify(list, Mockito.times(4)).get(Mockito.anyInt());//called in sysout I think??
	}
	
	@Test
	public void test_Answer() {
		ArrayList list = Mockito.mock(ArrayList.class);
		
		Mockito.when(list.size()).thenAnswer(new Answer<Integer>() {
			int count = 0;
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				return ++count;//*here based on count we can call different logic/service and return as result and test
			}
		});
		
		assertEquals(1, list.size());
		assertEquals(2, list.size());
		assertEquals(3, list.size());
	}
}
