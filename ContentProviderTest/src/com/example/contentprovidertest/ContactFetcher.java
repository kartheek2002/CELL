package com.example.contentprovidertest;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.content.CursorLoader;

public class ContactFetcher {
	private Context context;

	public ContactFetcher(Context c) {
		this.context = c;
	}

	public ArrayList<Contact> fetchAll() {
		ArrayList<Contact> listContacts = new ArrayList<Contact>();
		CursorLoader cursorLoader = new CursorLoader(context, RawContacts.CONTENT_URI, 
				null, // the columns to retrieve (all)
				null, // the selection criteria (none)
				null, // the selection args (none)
				null // the sort order (default)
		);

		Cursor c = cursorLoader.loadInBackground();
		if (c.moveToFirst()) {
			do {
				Contact contact = loadContactData(c);
				listContacts.add(contact);
			} while (c.moveToNext());
		}
		c.close();
		return listContacts;
	}

	private Contact loadContactData(Cursor c) {
		// Get Contact ID
		int idIndex = c.getColumnIndex(ContactsContract.Contacts._ID);
		String contactId = c.getString(idIndex);
		// Get Contact Name
		int nameIndex = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
		String contactDisplayName = c.getString(nameIndex);
		
		Contact contact = new Contact(contactId, contactDisplayName);
		return contact;
	}
}