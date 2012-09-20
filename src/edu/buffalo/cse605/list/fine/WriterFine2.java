package edu.buffalo.cse605.list.fine;

public class WriterFine2<T> {
	 private CursorFine<T> cursor; 
	 	
		public WriterFine2(CursorFine<T> cursor) {
			this.cursor=cursor;
		}

		public boolean insertBefore(T val) {
			ElementFine<T> e = new ElementFine<T>(val);
			ElementFine<T> prev = cursor.getprev();
			ElementFine<T> curr = cursor.curr();
			try {
				        prev.nextlock2.writerightlock();
						curr.prevlock2.writeleftlock();
					// Make sure they are still pointing to the ones they were supposed to point
					// This messes the performance
					if ( prev.next() == curr && 
						curr.prev() == prev ) {
						if ( cursor.curr() == null ) {
							throw new Exception("point to null");
						} else {
							curr.addBefore(e);
							prev.nextlock2.unwriterightlock();
							curr.nextlock2.unwriteleftlock();
							cursor.prev();
						}
						
					} else {
						throw new Exception("inconsitent");
					}
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return true;
		}


		public boolean insertAfter(T val) {
			ElementFine<T> e = new ElementFine<T>(val);
			ElementFine<T> next = cursor.getnext();
			ElementFine<T> curr = cursor.curr();
			try {
				 curr.nextlock2.writerightlock();
				 next.prevlock2.writeleftlock();
					// Make sure they are still pointing to the ones they were supposed to point
					// This messes the performance
					if ( next.prev() == cursor.curr() && 
						 curr.next() == next ) {
						if ( cursor.curr() == null ) {
							 throw new Exception("point to null");
						} else {
							curr.addAfter(e);
							curr.nextlock2.unwriterightlock();
							next.prevlock2.unwriteleftlock();
							cursor.next();
						}
					} else {
						throw new Exception("inconsitent");
					}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			return true;
		}

		public boolean delete() {
			ElementFine<T> prev = cursor.getprev();
			ElementFine<T> curr = cursor.curr();
			ElementFine<T> next = cursor.getnext();
			
			try {
				prev.nextlock2.writerightlock();
				curr.prevlock2.writeleftlock();
				curr.prevlock2.writerightlock();
				next.prevlock2.writeleftlock();
					// Make sure they are still pointing to the ones they were supposed to point
					// This messes the performance
					if ( prev.next() == curr && 
						curr.prev() == prev &&
						curr.next() == next &&
						next.prev() == curr ) {
						if (cursor.curr() == null) { 
							throw new Exception("the list is empty");
						} else if (cursor.getnext() == cursor.getprev() && cursor.getnext() == cursor.curr()) {
							cursor.curr(null);
							prev.nextlock2.unwriterightlock();
							curr.prevlock2.unwriteleftlock();
							curr.nextlock2.unwriterightlock();
							next.prevlock2.unwriteleftlock();
						} else {
							// 	Delete
							cursor.curr().delete();
							// Move the cursor to previous
							//before move, should release all the locks 
							prev.nextlock2.unwriterightlock();
							curr.prevlock2.unwriteleftlock();
							curr.nextlock2.unwriterightlock();
							next.prevlock2.unwriteleftlock();
							cursor.curr(prev);
						}
						
						
					} else {
						throw new Exception("inconsitent");
					}
				    
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return true;
		}
		
}
