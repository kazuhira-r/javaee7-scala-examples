package org.littlewings.javaee7.scoped

import javax.enterprise.context.ApplicationScoped

class Book

trait Shop[T]

@ApplicationScoped
class Business

@ApplicationScoped
class BookShop extends Business with Shop[Book]
