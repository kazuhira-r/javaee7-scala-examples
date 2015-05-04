package org.littlewings.javaee7

import javax.enterprise.context.ApplicationScoped

class Book

trait Shop[T]

class Business

@ApplicationScoped
class BookShop extends Business with Shop[Book]
