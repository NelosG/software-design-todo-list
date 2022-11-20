package ru.ifmo.pga.software.design.todo.list.service.impl

import org.springframework.beans.factory.annotation.Autowired
import ru.ifmo.pga.software.design.todo.list.dao.Dao
import ru.ifmo.pga.software.design.todo.list.entity.AbstractEntity
import ru.ifmo.pga.software.design.todo.list.service.Service
import ru.ifmo.pga.software.design.todo.list.service.exceptions.ServiceException

/**
 * @author Gleb Pushkarev
 * @since 1.0.0
 */
abstract class ServiceImpl<T : AbstractEntity, DAO : Dao<T>> : Service<T> {
    @Autowired
    protected lateinit var dao: DAO

    override fun findAll(): List<T> {
        return invokeDaoMethod { obj: DAO? -> obj!!.findAll() }
    }

    override fun findById(id: Long): T {
        return invokeDaoMethod<Long, T>(id) { obj: DAO, id: Long -> obj.findById(id) }
    }

    override fun save(entity: T): T {
        return invokeDaoMethod(entity) { obj: DAO, entity: T -> obj.save(entity) }
    }

    override fun save(entities: Collection<T>): List<T> {
        return invokeDaoMethod(entities) { obj: DAO, entities: Collection<T> -> obj.save(entities) }
    }

    override fun remove(entity: T) {
        remove(entity.id)
    }

    override fun remove(id: Long) {
        try {
            dao.remove(id)
        } catch (e: Exception) {
            throw createDataAccessError(e)
        }
    }

    fun <R> invokeDaoMethod(function: Function1<DAO, R>): R {
        return try {
            function.invoke(dao)
        } catch (e: Exception) {
            throw createDataAccessError(e)
        }
    }

    fun <P, R> invokeDaoMethod(param: P, function: Function2<DAO, P, R>): R {
        return try {
            function.invoke(dao, param)
        } catch (e: Exception) {
            throw createDataAccessError(e)
        }
    }

    fun <P1, P2, R> invokeDaoMethod(param1: P1, param2: P2, function: Function3<DAO, P1, P2, R>): R {
        return try {
            function.invoke(dao, param1, param2)
        } catch (e: Exception) {
            throw createDataAccessError(e)
        }
    }

    private fun createDataAccessError(e: Exception): ServiceException {
        //TODO: add logs
        return ServiceException("Failed to access DB: ${e.message}", e)
    }

}
